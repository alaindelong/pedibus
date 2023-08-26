package com.example.pedibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/recover")
	public void recoverPassword(@RequestBody String email) {
		//TODO recoit une email sur laquelle envoyer un lien
	}
	@GetMapping("/recover/{randomUUID}")
	public String resetPassword(@PathVariable String randomUUID) {
		//TODO
		return "lien";
	}
	@PostMapping("/recover/{randomUUID}")
	public String checkResetPassword(@PathVariable String randomUUID) {
		//TODO  request body PasswordDto
		return "lien";
	}
}
