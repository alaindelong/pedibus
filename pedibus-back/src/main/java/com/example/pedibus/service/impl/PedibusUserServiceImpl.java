package com.example.pedibus.service.impl;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Accompagnatore;
import com.example.pedibus.model.ConfirmationToken;
import com.example.pedibus.model.Passaggero;
import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.model.Role;
import com.example.pedibus.repository.PedibusUserRepository;
import com.example.pedibus.repository.RoleRepository;
import com.example.pedibus.security.ApplicationUserRole;
import com.example.pedibus.service.ConfirmationTokenService;
import com.example.pedibus.service.EmailService;
import com.example.pedibus.service.PedibusUserService;

@Service
@Transactional
public class PedibusUserServiceImpl implements PedibusUserService{

	@Autowired
   private PasswordEncoder passwordEncoder;	
	@Autowired
	private PedibusUserRepository pedibusUserRepository;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception {
		if(pedibusUser != null && pedibusUserRepository.findByUsername(pedibusUser.getUsername())!=null)
			throw new Exception("username "+pedibusUser.getUsername()+" already exists");
		//PedibusUser p = new PedibusUser();
		PedibusUser p = new PedibusUser();
		if(pedibusUser instanceof Passaggero)
		 p = new Passaggero();
		if(pedibusUser instanceof Accompagnatore)
			p = new Accompagnatore();
		p.setUsername(pedibusUser.getUsername());
		p.setNome(pedibusUser.getNome());
		p.setEmail(pedibusUser.getEmail());
		p.setPassword(passwordEncoder.encode(pedibusUser.getPassword()));
		if(pedibusUser.getRole()==null) {
			p.setRole(ApplicationUserRole.USER.name());
			p.getAuthorities().add(new Role(ApplicationUserRole.USER.name()));
		}	
		else {
			if(!pedibusUser.getRole().equals(ApplicationUserRole.USER.name())) {
				p.setRole(pedibusUser.getRole());
				p.getAuthorities().add(new Role(ApplicationUserRole.USER.name()));
				p.getAuthorities().addAll(pedibusUser.getAuthorities());
			}else if(pedibusUser.getRole().equals(ApplicationUserRole.USER.name())) {
				p.setRole(pedibusUser.getRole());
				p.getAuthorities().add(new Role(ApplicationUserRole.USER.name()));
			}
			
			
		}
		//p.setRole(ApplicationUserRole.USER.name());
		//p.getAuthorities().add(new Role("ROLE_"+ApplicationUserRole.USER.name()));
		p.setAccountNonExpired(true);
		p.setAccountNonLocked(true);
		p.setCredentialsNonExpired(true);
		if(pedibusUser.getRole().equals(ApplicationUserRole.SYSTEM_ADMIN.name()))
		  p.setEnabled(true);
		else
			p.setEnabled(false);
		PedibusUser savedUser = pedibusUserRepository.save(p);
		Role role = new Role(pedibusUser.getRole());
		role.getPedibusUsers().add(p);
		roleRepository.save(role);
		
		//generate activation token to verify email
		String token = UUID.randomUUID().toString();
		ConfirmationToken ct = new ConfirmationToken();
		ct.setToken(token);
		ct.setPedibusUser(savedUser);
		ct.setCreatedAt(LocalDateTime.now());
		ct.setExpiresAt(LocalDateTime.now().plusMinutes(60));
		ConfirmationToken savedToken = confirmationTokenService.savedToken(ct);
		System.out.println("generated token "+token);
		//send email
		/*emailService.send(savedUser.getEmail(), 
				"Confirm your email",
				"Bonjour, clicquez sur le lien pour confirmer votre email "+
		        "http://localhost:8082/register/confirm?token="+
				token);*/
		return savedUser;
	}

	@Override
	public PedibusUser findByUsername(String username) {
		
		return pedibusUserRepository.findByUsername(username);
	}


	@Override
	public PedibusUser updatePedibusUser(PedibusUser pedibusUser, Long id) throws Exception {
		PedibusUser p = pedibusUserRepository.getReferenceById(id);
		if(p==null)
			throw new Exception("user with id "+id+" not found");
		p.setUsername(pedibusUser.getUsername());
		p.setNome(pedibusUser.getNome());
		p.setEmail(pedibusUser.getEmail());
		p.setPassword(passwordEncoder.encode(pedibusUser.getPassword()));
		p.setRole(pedibusUser.getRole());
		p.setAccountNonExpired(pedibusUser.isAccountNonExpired());
		p.setAccountNonLocked(pedibusUser.isAccountNonLocked());
		p.setCredentialsNonExpired(pedibusUser.isCredentialsNonExpired());
		p.setEnabled(pedibusUser.isEnabled());
		return pedibusUserRepository.save(p);
	}

	@Override
	public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers) throws Exception {
		List<PedibusUser> result=new ArrayList<PedibusUser>();
		for(PedibusUser p :pedibusUsers) {
			//p.setPassword(passwordEncoder.encode(p.getPassword()));
			result.add(addPedibusUser(p));
		}
		return result;
	}

	@Override
	public List<PedibusUser> getAllPedibusUser() {
		
		return pedibusUserRepository.findAll();
	}

	@Override
	public PedibusUser getPedibusUser(Long id) {
		return pedibusUserRepository.getReferenceById(id);
	}

	@Override
	public String enableUser(String username) {
		PedibusUser p = findByUsername(username);
		p.setEnabled(true);
		return "confirmed";
	}
	
	public boolean addOrRemoveAuthority(String username,String operation, String role) {
		PedibusUser p = findByUsername(username);
		if(p==null)
			throw new IllegalStateException("user not found username = "+username);
		if(operation.equals("add")) {
			p.getAuthorities().add(new Role(role));
			return pedibusUserRepository.save(p)!=null?true:false;
			
		}else if(operation.equals("remove")) {
			p.setAuthorities(p.getAuthorities().stream()
			.filter(r -> !r.getRole().equals("ROLE_"+role))
			.collect(Collectors.toSet()));
		// return	p.getAuthorities().remove(new Role("ROLE_"+role));
			return pedibusUserRepository.save(p)!=null?true:false;
		}else {
			throw new IllegalStateException("unknwon operation = "+operation);
		}
	}

}
