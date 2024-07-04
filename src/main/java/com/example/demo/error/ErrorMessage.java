package com.example.demo.error;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
	
	private String message;
	
	private Date date;
	
	private int statusCode;

}
