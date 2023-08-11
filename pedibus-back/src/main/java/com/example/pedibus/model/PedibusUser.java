package com.example.pedibus.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedibusUser implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private  String role;
	//private final Set<? extends GrantedAuthority> authorities;
	private  String password;
	private  String username;
	private String email;
	private String nome;
	private  boolean isAccountNonExpired;
	private  boolean isAccountNonLocked;
	private  boolean isCredentialsNonExpired;
	private  boolean isEnabled;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new Role("ROLE_"+this.role));
		return authorities;
	}

}
