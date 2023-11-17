package com.example.pedibus.service.impl;

import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.pedibus.service.EmailService;

import org.slf4j.*;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	//@Autowired
	private JavaMailSender javaMailSender;
	
	//@Value("${application.regexEmail}")
	private String regex;

	@Override
	public boolean isEmailValid(String email) {
	/*	Pattern.compile(regex)
	      .matcher(email)
	      .matches();*/ 
		return true;
	}

	@Override
	public void send(String to, String subject, String text) {

     try {
    	 SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(to);
         message.setSubject(subject);
         message.setText(text);

         javaMailSender.send(message);
     }catch(MailException e) {
    	 logger.error("failed to send email: cause "+e.getMessage());
    	 //throw new IllegalStateException("failed to send email");
     }
		
	}

}
