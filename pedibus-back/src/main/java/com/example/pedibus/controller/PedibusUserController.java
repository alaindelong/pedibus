package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.security.TokenProvider;
import com.example.pedibus.service.EmailService;
import com.example.pedibus.service.PedibusUserService;

@CrossOrigin
@RestController
public class PedibusUserController {
    @Autowired
	private PedibusUserService pedibusUserService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/login")
	public String addPedibusUser(@RequestBody PedibusUser pedibusUser) throws Exception {
    	System.out.println("login");
		//return pedibusUserService.addPedibusUser(pedibusUser);
    	return "login";
	}
    @PostMapping("/register")
	public String register(@RequestBody PedibusUser pedibusUser) throws Exception {
    	if(!emailService.isEmailValid(pedibusUser.getEmail())) {
    		throw new IllegalAccessException("email not valid "+pedibusUser.getEmail());
    	}
    	PedibusUser p =pedibusUserService.findByUsername(pedibusUser.getUsername());
    	if(p != null) {
    		System.out.println("user with username "+pedibusUser.getUsername()+" already exists");
    		throw new IllegalAccessException("user with username "+pedibusUser.getUsername()+" already exists");
    	}
    	System.out.println("input user "+pedibusUser.getEmail());
    	PedibusUser pe = pedibusUserService.addPedibusUser(pedibusUser);
    	System.out.println("register");
		return tokenProvider.createToken(pe);
	}
    @GetMapping("/users/{username}")
	public PedibusUser findByUsername(@PathVariable String username) {
		return pedibusUserService.findByUsername(username);
	}
    @PutMapping("/users/{id}")
	public PedibusUser updatePedibusUser(@RequestBody PedibusUser pedibusUser,@PathVariable Long id) throws Exception{
		return pedibusUserService.updatePedibusUser(pedibusUser, id);
	}
	public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers) throws Exception{
		return pedibusUserService.addPedibusUsers(pedibusUsers);
	}
	@GetMapping("/users")
	public List<PedibusUser> getAllPedibusUser(){
		return pedibusUserService.getAllPedibusUser();
	}
	/*("/users/{id}")
	public PedibusUser getPedibusUser(@PathVariable Long id) {
		return pedibusUserService.getPedibusUser(id);
	}*/
	@GetMapping("/users/{username}/{operation}/{role}")
	public boolean addOrRemoveAuthority(@PathVariable String username,@PathVariable String operation,@PathVariable String role) {
		return pedibusUserService.addOrRemoveAuthority(username, operation, role);
	}
}
