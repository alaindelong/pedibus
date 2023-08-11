package com.example.pedibus.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {
	private String role;
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
