package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.ResponseMessage;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping(value="/api/v1/caterers")
public class CatererApiController {
	
	private CustomerService customerService;
	
	private ModelMapper modelMapper;
	
	public CatererApiController(CustomerService customerService, ModelMapper modelMapper) {
		super();
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping(value="/{customerId}")
	public ResponseEntity<ResponseMessage> createCateringService(@PathVariable("customerId") String customerId) {
		
		customerService.createCateringService(customerId);
	
	
	}
}
