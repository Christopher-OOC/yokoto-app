package com.example.demo.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.request.CustomerRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerApiController {
	
	private CustomerService customerService;
	
	private ModelMapper modelMapper;
	
	public CustomerApiController(CustomerService customerService, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessage> registerACustomer(@RequestBody @Valid CustomerRequestModel customerInRequest) {
				
		CustomerDto customerDto = modelMapper.map(customerInRequest, CustomerDto.class);
		
		customerService.createCustomer(customerDto);
		
		ResponseMessage message = new ResponseMessage();
		message.setRequestStatus(RequestStatus.REGISTERED);
		message.setResponseStatus(ResponseStatus.SUCCESS);
		
		return ResponseEntity.created(null).body(message);
	}
}
