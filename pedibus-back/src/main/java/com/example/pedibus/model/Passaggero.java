package com.example.pedibus.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "PASS")
public class Passaggero extends PedibusUser {

	private static final long serialVersionUID = 1L;

	public Passaggero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passaggero(Long id, String role, Set<Role> authorities, String password, String username, String email,
			String nome, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		super(id, role, authorities, password, username, email, nome, isAccountNonExpired, isAccountNonLocked,
				isCredentialsNonExpired, isEnabled);
		// TODO Auto-generated constructor stub
	}

}
