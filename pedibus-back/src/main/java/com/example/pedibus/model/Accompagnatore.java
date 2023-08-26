package com.example.pedibus.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ACC")
@NoArgsConstructor
public class Accompagnatore extends PedibusUser {
	private static final long serialVersionUID = 1L;

	public Accompagnatore(Long id, String role, Set<Role> authorities, String password, String username, String email,
			String nome, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		super(id, role, authorities, password, username, email, nome, isAccountNonExpired, isAccountNonLocked,
				isCredentialsNonExpired, isEnabled);
		// TODO Auto-generated constructor stub
	}

}
