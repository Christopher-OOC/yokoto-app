package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.ResponseMessage;

@RestController
@RequestMapping(value="/api/v1/caterers")
public class CatererApiController {
	
	@PostMapping(value="/{customerId}")
	public ResponseEntity<ResponseMessage> createCateringService(@PathVariable("customerId") String customerId) {
		
	}

}
