package com.example.pedibus.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.repository.PedibusUserRepository;
import com.example.pedibus.service.PedibusUserService;

@Service
@Transactional
public class PedibusUserServiceImpl implements PedibusUserService,
UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PedibusUserRepository pedibusUserRepository;
	@Override
	public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception {
		if(pedibusUser != null && pedibusUserRepository.findByUsername(pedibusUser.getUsername())!=null)
			throw new Exception("username "+pedibusUser.getUsername()+" already exists");
		PedibusUser p = new PedibusUser();
		p.setUsername(pedibusUser.getUsername());
		p.setNome(pedibusUser.getNome());
		p.setEmail(pedibusUser.getEmail());
		p.setPassword(passwordEncoder.encode(pedibusUser.getPassword()));
		p.setRole(pedibusUser.getRole());
		p.setAccountNonExpired(true);
		p.setAccountNonLocked(true);
		p.setCredentialsNonExpired(true);
		p.setEnabled(true);
		return pedibusUserRepository.save(p);
	}

	@Override
	public PedibusUser findByUsername(String username) {
		
		return pedibusUserRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails u = pedibusUserRepository.findByUsername(username);
		if(u==null)
			throw new UsernameNotFoundException("user with username "+username+" not found");
		return u;
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
		return pedibusUserRepository.saveAll(pedibusUsers);
	}

	@Override
	public List<PedibusUser> getAllPedibusUser() {
		
		return pedibusUserRepository.findAll();
	}

}
