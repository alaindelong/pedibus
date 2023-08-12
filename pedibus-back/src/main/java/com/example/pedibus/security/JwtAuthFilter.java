package com.example.pedibus.security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
public JwtAuthFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
	
  try {
	UsernameAndPasswordAuthRequest upr = new ObjectMapper()
			  .readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);
	
	Authentication authentication = new UsernamePasswordAuthenticationToken(
			upr.getUsername(), 
			upr.getPassword());
   return authenticationManager.authenticate(authentication);
} catch (Exception e) {
	throw new RuntimeException(e.getMessage());
}
}

//to generate token if the first methos succeed
@Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
String key ="securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
String token = Jwts.builder()
    .setSubject(authResult.getName())
    .claim("authorities",authResult.getAuthorities())
    .setIssuedAt(new Date())
    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
    .signWith(Keys.hmacShaKeyFor(key.getBytes()))
    .compact();
 response.setHeader("Authorization","Bearer "+ token);
	
}
}
