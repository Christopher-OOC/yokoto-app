package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CatererAssistantRequestModel;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1/caterer-assistants")
public class CatererAssistantApiController {
	
	private CustomerRepository customerRepository;
	
	private ModelMapper modelMapper;
	
	public CatererAssistantApiController(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/{customerId}")
	public ResponseEntity<ResponseMessage> createCatererAssistant(@PathVariable("customerId") String customerId,
			@RequestBody CatererAssistantRequestModel requestModel) {
		
		
		return ResponseEntity.ok(null);
	}
	

}
