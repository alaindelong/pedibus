package com.example.pedibus.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtVerifierToken extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader!=null&& (authorizationHeader.equals("") || !authorizationHeader.startsWith("Bearer"))) {
			filterChain.doFilter(request, response);
			return;
		}
		if(authorizationHeader==null) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorizationHeader.replace("Bearer ", "");
		try {
			
			String key ="securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
			Jws<Claims> jwtClaims = Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build()
			.parseClaimsJws(token);
			Claims body = jwtClaims.getBody();
			String username = body.getSubject();
			List<Map<String,String>> authorities = (List<Map<String, String>>) body.get("authorities");
			Set<SimpleGrantedAuthority> sgt = authorities.stream()
					 .map(s -> new SimpleGrantedAuthority(s.get("authority")))
					 .collect(Collectors.toSet());
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, sgt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch(Exception e) {
			throw new IllegalStateException("token cannot be trusted "+token);
		}
		filterChain.doFilter(request, response);
	}

}
