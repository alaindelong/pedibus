package com.example.pedibus.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.ConfirmationToken;
import com.example.pedibus.repository.ConfirmationTokenRepository;
import com.example.pedibus.service.ConfirmationTokenService;
import com.example.pedibus.service.PedibusUserService;

@Service
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
	@Autowired
private ConfirmationTokenRepository confirmationTokenRepository;
	/*@Autowired
	private PedibusUserService pedibusUserService;*/
	@Override
	public ConfirmationToken savedToken(ConfirmationToken token) {
		
		return confirmationTokenRepository.save(token);
	}
	@Override
	public String confirmToken(String token) throws IllegalAccessException {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
		if(confirmationToken ==null)
			throw new IllegalAccessException("token "+token+" not found");
		if(confirmationToken.getConfirmedAt() !=null)
			throw new IllegalAccessException("email already confirmed");
		if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()))
			throw new IllegalAccessException("token expired");
		confirmationToken.setConfirmedAt(LocalDateTime.now());
		//pedibusUserService.enableUser(confirmationToken.getPedibusUser().getUsername());
		confirmationToken.getPedibusUser().setEnabled(true);
		return "confirmed";
	}

}
