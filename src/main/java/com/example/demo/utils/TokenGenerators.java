package com.example.demo.utils;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.example.demo.security.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerators {
	
	private static final Random RANDOM = new SecureRandom();
	
	private static final String TEXT_GENERATOR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	
	public static String generateEmailVerificationToken(String email) {
		
		byte[] secretTokenByte = Base64.getEncoder().encode(SecurityConstants.TOKEN_SECRET.getBytes());
		
		SecretKey key = new SecretKeySpec(secretTokenByte, SignatureAlgorithm.HS512.getJcaName());
		
		Instant now = Instant.now();
		
		String token = Jwts.builder()
			.signWith(key)
			.subject(email)
			.issuedAt(new Date())
			.expiration(Date.from(now.plusSeconds(SecurityConstants.TOKEN_EXPIRATION)))
			.compact();
		
		return token;
	}
	

}
