package com.example.pedibus.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeUser",discriminatorType = DiscriminatorType.STRING)
public  class PedibusUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private  String role;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(joinColumns =@JoinColumn(name= "pedibusUserId"),
	inverseJoinColumns =@JoinColumn(name= "roleId"))
	private Set<Role> authorities = new HashSet<Role>();
	//private Set<Role> authorities;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(joinColumns =@JoinColumn(name= "pedibusUserId"),
			inverseJoinColumns =@JoinColumn(name= "notificationId"))
	private Set<Notification> notifications;
	private  String password;
	private  String username;
	private String email;
	private String nome;
	@Column(insertable = false,updatable = false)
	private String typeUser;
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


	public PedibusUser(Long id, String role, Set<Role> authorities, String password, String username, String email,
			String nome, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		super();
		this.id = id;
		this.role = role;
		this.authorities = authorities;
		this.password = password;
		this.username = username;
		this.email = email;
		this.nome = nome;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

}
