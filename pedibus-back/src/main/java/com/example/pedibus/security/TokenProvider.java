package com.example.pedibus.security;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.pedibus.model.PedibusUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	String key ="securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
	
	public String createToken(PedibusUser p) {
		
			String token = Jwts.builder()
				    .setSubject(p.getUsername())
				    .claim("authorities",p.getAuthorities())
				    .setIssuedAt(new Date())
				    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				    .signWith(Keys.hmacShaKeyFor(key.getBytes()))
				    .compact();
		
		return token;
	}
}
