package com.example.pedibus.service.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.pedibus.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Value("${application.regexEmail}")
	private String regex;

	@Override
	public boolean isEmailValid(String email) {
	/*	Pattern.compile(regex)
	      .matcher(email)
	      .matches();*/ 
		return true;
	}

}
