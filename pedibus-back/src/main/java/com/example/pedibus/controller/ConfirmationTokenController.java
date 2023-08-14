package com.example.pedibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.service.ConfirmationTokenService;

@RestController
public class ConfirmationTokenController {
	@Autowired
private ConfirmationTokenService confirmationTokenService;
	
	@GetMapping("/register/confirm")
	public String confirmToken(@RequestParam("token") String token) throws IllegalAccessException {
		return confirmationTokenService.confirmToken(token);
	}
}
