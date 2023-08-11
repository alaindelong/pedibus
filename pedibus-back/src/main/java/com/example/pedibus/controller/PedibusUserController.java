package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.service.PedibusUserService;

@RestController
public class PedibusUserController {
    @Autowired
	private PedibusUserService pedibusUserService;
    
	public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception {
		return pedibusUserService.addPedibusUser(pedibusUser);
	}
	public PedibusUser findByUsername(String username) {
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
