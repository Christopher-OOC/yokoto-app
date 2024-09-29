package com.example.demo.error;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
	
	private String message;

	@JsonFormat(pattern = "yyyy-MM-dd 'T' hh:mm:ss")
	private Date date;
	
	private int statusCode;

}
