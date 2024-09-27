package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		AuthenticationManager authenticationManager = builder.build();

		CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager);
		authenticationFilter.setFilterProcessesUrl(SecurityConstants.SIGNIN_URL);

		CustomAuthorizationFilter authorizationFilter = new CustomAuthorizationFilter(authenticationManager);


		http.authorizeHttpRequests(request -> request
				.requestMatchers(HttpMethod.POST, SecurityConstants.SIGNUP_URL).permitAll()
				.requestMatchers(HttpMethod.POST, SecurityConstants.SIGNIN_URL).permitAll()
				.anyRequest().authenticated()
			);


		 http.addFilter(authenticationFilter);
		 http.addFilter(authorizationFilter);
		 http.authenticationManager(authenticationManager);
		 
		 http.sessionManagement(session -> session
				 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);
		 
		 http.csrf(csrf -> csrf.disable());

		 return http.getOrBuild();
	}
}
