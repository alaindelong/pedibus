package com.example.pedibus.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pedibus.security.ApplicationUserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedibusUser implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private  String role;
	@ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(joinColumns =@JoinColumn(name= "pedibusUserId"),
	inverseJoinColumns =@JoinColumn(name= "roleId"))
	private Set<Role> authorities = new HashSet<Role>();
	//private Set<Role> authorities;
	private  String password;
	private  String username;
	private String email;
	private String nome;
	private  boolean isAccountNonExpired;
	private  boolean isAccountNonLocked;
	private  boolean isCredentialsNonExpired;
	private  boolean isEnabled;
	
	

	@Override
	public Collection< Role> getAuthorities() {
		/*Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new Role("ROLE_"+this.role));*/
		return authorities;
	}
	
	
	public Collection< Role> removeAuthorities(String role) {
		/*Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) getAuthorities();
		authorities.remove(new Role("ROLE_"+role));
		//authorities.add(new Role("ROLE_"+role));*/
		return authorities;
	}
	public Collection<Role> addAuthorities(String role) {
		/*Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) getAuthorities();
		//authorities.remove(new Role("ROLE_"+role));*/
		authorities.add(new Role("ROLE_"+role));
		return authorities;
	}

}
