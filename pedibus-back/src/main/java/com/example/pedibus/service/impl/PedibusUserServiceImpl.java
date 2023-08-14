package com.example.pedibus.service.impl;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.ConfirmationToken;
import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.repository.PedibusUserRepository;
import com.example.pedibus.security.ApplicationUserRole;
import com.example.pedibus.service.ConfirmationTokenService;
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
	@Override
	public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception {
		if(pedibusUser != null && pedibusUserRepository.findByUsername(pedibusUser.getUsername())!=null)
			throw new Exception("username "+pedibusUser.getUsername()+" already exists");
		PedibusUser p = new PedibusUser();
		p.setUsername(pedibusUser.getUsername());
		p.setNome(pedibusUser.getNome());
		p.setEmail(pedibusUser.getEmail());
		p.setPassword(passwordEncoder.encode(pedibusUser.getPassword()));
		if(pedibusUser.getRole()==null)
			p.setRole(ApplicationUserRole.USER.name());
		else p.setRole(pedibusUser.getRole());
		p.setAccountNonExpired(true);
		p.setAccountNonLocked(true);
		p.setCredentialsNonExpired(true);
		p.setEnabled(false);
		PedibusUser savedUser = pedibusUserRepository.save(p);
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
	public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers) {
		List<PedibusUser> result=new ArrayList<PedibusUser>();
		for(PedibusUser p :pedibusUsers) {
			p.setPassword(passwordEncoder.encode(p.getPassword()));
			result.add(p);
		}
		return pedibusUserRepository.saveAll(result);
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

}
