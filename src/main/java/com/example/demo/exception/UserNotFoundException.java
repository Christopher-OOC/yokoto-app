package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3204705332368137965L;
	
	public UserNotFoundException(String publicId) {
		super("No user found with ID: " + publicId);
	}

}
