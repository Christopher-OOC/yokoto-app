package com.example.demo.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.demo.CustomApplicationContext;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.request.UserLoginRequestModel;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		UserLoginRequestModel creds = null;
		
		try {
			creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String email = ((User)authResult.getPrincipal()).getUsername();
		
		CustomerService customerService = (CustomerService)CustomApplicationContext.getBean("customerServiceImpl");
		CustomerDto customer = customerService.findByEmail(email);
		
		String customerId = customer.getCustomerId();
		
		byte[] secretByte = Base64.getEncoder().encode(SecurityConstants.TOKEN_SECRET.getBytes());
		
		SecretKey key = new SecretKeySpec(secretByte, SignatureAlgorithm.HS512.getJcaName());
		
		String token = Jwts.builder()
			.signWith(key)
			.subject(email)
			.issuedAt(new Date())
			.expiration(Date.from(Instant.now().plusSeconds(SecurityConstants.TOKEN_EXPIRATION)))
			.compact();
		
		response.addHeader(SecurityConstants.AUTHORIZATION_HEADER_PREFIX, SecurityConstants.AUTHORIZATION_TOKEN_PREFIX + token);
		response.addHeader("customerId", customerId);
	}
}
