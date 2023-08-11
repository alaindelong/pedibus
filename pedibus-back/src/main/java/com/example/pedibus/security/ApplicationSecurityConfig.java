package com.example.pedibus.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http .csrf().disable()
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/").permitAll()
            		.antMatchers("/h2/**").permitAll()
            		//.antMatchers("/reservations/**").hasRole(ApplicationUserRole.ADMIN.name())
            		//.antMatchers(HttpMethod.POST, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.PUT, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.DELETE, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.GET, "/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
               // .anyRequest().authenticated()
            		//.anyRequest().permitAll()
            )
            .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() { //fetch data from db
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user"))
            //.roles(ApplicationUserRole.USER.name()) //ROLE_USER
            .authorities(ApplicationUserRole.USER.getGrantedAuthority())
            .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
               // .roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMIN
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthority())
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
