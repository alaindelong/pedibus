package com.example.pedibus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pedibus.service.PedibusUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PedibusUserService pedibusUserService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails u = pedibusUserService.findByUsername(username);
		if(u==null)
			throw new UsernameNotFoundException("user with username "+username+" not found");
		return u;
	}

}
