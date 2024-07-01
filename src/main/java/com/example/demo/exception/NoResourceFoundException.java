package com.example.demo.exception;

public class NoResourceFoundException extends RuntimeException {

	private static final long serialVersionUID = 5567260534087985460L;
	
	public NoResourceFoundException(String message) {
		super(message);
	}

}
