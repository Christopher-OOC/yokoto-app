package com.example.demo.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.demo.CustomApplicationContext;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthorizationFilter extends BasicAuthenticationFilter {

	public CustomAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		System.out.println("A");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("A");
		chain.doFilter(request, response);
		System.out.println("A");
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String authorizationHeaderWithStringBearer = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER_PREFIX);

		if (authorizationHeaderWithStringBearer == null || authorizationHeaderWithStringBearer.isBlank()) {
			return null;
		}
		
		String actualToken = authorizationHeaderWithStringBearer.replace(SecurityConstants.AUTHORIZATION_TOKEN_PREFIX, "");

		byte[] secretByte = Base64.getEncoder().encode(SecurityConstants.TOKEN_SECRET.getBytes());

		SecretKey key = new SecretKeySpec(secretByte, SignatureAlgorithm.HS512.getJcaName());

		JwtParser parser = Jwts.parser().setSigningKey(key).build();

		Jwt<?, ?> jwt = parser.parse(actualToken);
		Claims payload = (Claims) jwt.getPayload();

		String email = payload.getSubject();

		Date now = new Date();

		Date tokenExpiration = payload.getExpiration();

		if (tokenExpiration.before(now)) {
			return null;
		}

		UserService userService = (UserService) CustomApplicationContext.getBean("userServiceImpl");

		UserDto userDto = userService.findByEmail(email);
		
		System.out.println(userDto);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(),
				null, Collections.emptyList());

		System.out.println("HERE 2");
		return authentication;
	}
}
