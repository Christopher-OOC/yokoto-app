package com.example.demo.exception;

public class InvalidEmailException extends RuntimeException {

	private static final long serialVersionUID = 588193917046295795L;

	public InvalidEmailException() {
		super("Please provide a valid email");
	}
	
}
