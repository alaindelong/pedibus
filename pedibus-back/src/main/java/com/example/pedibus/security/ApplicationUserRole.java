package com.example.pedibus.security;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
SYSTEM_ADMIN(Set.of(ApplicationUserPermission.USER_READ,
		ApplicationUserPermission.USER_WRITE,
		ApplicationUserPermission.ADMIN_READ,
		ApplicationUserPermission.ADMIN_WRITE)),
ADMIN(Set.of(ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE)),
ACCOMPAGNATORE(Set.of()),
PASSAGGERO(Set.of()),
USER(Set.of());
private final Set<ApplicationUserPermission> permissions;

private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
	this.permissions = permissions;
}

public Set<ApplicationUserPermission> getPermissions() {
	return permissions;
}
public Set <SimpleGrantedAuthority> getGrantedAuthority() {
	Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
	permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
	return permissions;
}
}
