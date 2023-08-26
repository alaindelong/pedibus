package com.example.pedibus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.pedibus.service.PedibusUserService;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http .csrf().disable()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
            .addFilter(new JwtAuthFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
            .addFilterAfter(new JwtVerifierToken(), JwtAuthFilter.class)
            .authorizeHttpRequests(auth -> auth
            		.antMatchers("/").permitAll()
            		.antMatchers("/h2/**").permitAll()
            		.antMatchers("/register","/register/**","/login","/confirm","/recover","/recover/**").permitAll()
            		.antMatchers("/users","/users/**").hasAnyRole(ApplicationUserRole.SYSTEM_ADMIN.name(),ApplicationUserRole.ADMIN.name())
            		//.antMatchers("/reservations/**").hasRole(ApplicationUserRole.ADMIN.name())
            		//.antMatchers(HttpMethod.POST, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.PUT, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.DELETE, "/management/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
            		//.antMatchers(HttpMethod.GET, "/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .anyRequest().authenticated()
            		//.anyRequest().permitAll()
            )
            //.httpBasic();
            .headers(headers -> headers.frameOptions().disable());
        return http.getOrBuild();
    }
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	     return authenticationConfiguration.getAuthenticationManager();
	}
	
	/*@Bean
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
    }*/
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
}
