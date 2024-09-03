package com.example.demo.security;

import java.util.Collections;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager manager;
	
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return args -> {
			
			User user = userRepository.findByEmail(args);
			
			if (user == null) {
				throw new UsernameNotFoundException("No user found!!!");
			}
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
		};
	}
	
	@Bean
	protected AuthenticationManager authenticationManger(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

		return builder.build();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsService());

		AuthenticationManager manager = builder.getObject();
		
		 http.authorizeHttpRequests(request -> request
				.requestMatchers(HttpMethod.POST, SecurityConstants.SIGNUP_URL).permitAll()
				.requestMatchers(HttpMethod.POST, SecurityConstants.SIGNIN_URL).permitAll()
				.anyRequest().authenticated()
			);
		 
		 CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(manager);
		 authenticationFilter.setFilterProcessesUrl(SecurityConstants.SIGNIN_URL);
		 
		 CustomAuthorizationFilter authorizationFilter = new CustomAuthorizationFilter(manager);
		 
		 http.addFilter(authenticationFilter);
		 http.addFilter(authorizationFilter);
		 
		 http.sessionManagement(session -> session
				 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);
		 
		 http.csrf(csrf -> csrf.disable()); 
		 
		 return http.build();
	}
}
