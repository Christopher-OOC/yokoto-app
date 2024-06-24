package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Random;

public class PublicIdGeneratorUtils {
	
	private static Random RANDOM = new SecureRandom();
	
	private static String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	
	public static String generatePublicId(int length) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			builder.append(ALPHABETS.charAt(RANDOM.nextInt(ALPHABETS.length())));
		}
		
		return builder.toString();
	}
}
