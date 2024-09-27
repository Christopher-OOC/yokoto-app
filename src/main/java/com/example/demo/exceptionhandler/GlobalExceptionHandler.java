package com.example.demo.exceptionhandler;

import java.util.Date;

import com.example.demo.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.error.ErrorMessage;
import com.example.demo.exception.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleGloalError(HttpServletRequest request, Exception ex) {
		
		ErrorMessage error = new ErrorMessage();
		error.setDate(new Date());
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return error;
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleNoResourceFoundException(HttpServletRequest request, NoResourceFoundException ex) {
		
		ErrorMessage error = new ErrorMessage();
		error.setDate(new Date());
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return error;
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleResourceAlreadyExistsException(HttpServletRequest request, ResourceAlreadyExistsException ex) {

		ErrorMessage error = new ErrorMessage();
		error.setDate(new Date());
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return error;
	}

}
