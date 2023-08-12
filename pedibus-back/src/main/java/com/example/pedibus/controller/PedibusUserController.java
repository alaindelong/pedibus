package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.security.TokenProvider;
import com.example.pedibus.service.PedibusUserService;

@RestController
public class PedibusUserController {
    @Autowired
	private PedibusUserService pedibusUserService;
    @Autowired
    private TokenProvider tokenProvider;
    
    @PostMapping("/login")
	public String addPedibusUser(@RequestBody PedibusUser pedibusUser) throws Exception {
    	System.out.println("login");
		//return pedibusUserService.addPedibusUser(pedibusUser);
    	return "login";
	}
    @PostMapping("/register")
	public String register(@RequestBody PedibusUser pedibusUser) throws Exception {
    	PedibusUser p =pedibusUserService.findByUsername(pedibusUser.getUsername());
    	if(p != null) {
    		System.out.println("user with username "+pedibusUser.getUsername()+" already exists");
    		return null;
    	}
    	PedibusUser pe = pedibusUserService.addPedibusUser(pedibusUser);
    	System.out.println("register");
		return tokenProvider.createToken(pe);
	}
    @GetMapping("/login/{username}")
	public PedibusUser findByUsername(@PathVariable String username) {
		return pedibusUserService.findByUsername(username);
	}
	public PedibusUser updatePedibusUser(PedibusUser pedibusUser, Long id) throws Exception{
		return pedibusUserService.updatePedibusUser(pedibusUser, id);
	}
	public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers){
		return pedibusUserService.addPedibusUsers(pedibusUsers);
	}
	public List<PedibusUser> getAllPedibusUser(){
		return pedibusUserService.getAllPedibusUser();
	}
}
