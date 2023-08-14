package com.example.pedibus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	@ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER)
	@JsonIgnore
	Set<PedibusUser> pedibusUsers = new HashSet<PedibusUser>();
	public Role(String role) {
		this.role ="ROLE_"+ role;
	}
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
