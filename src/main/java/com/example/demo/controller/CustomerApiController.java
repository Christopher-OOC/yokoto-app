package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
public class CustomerApiController {
	
	private CustomerService customerService;
	
	private ModelMapper modelMapper;
	
	public CustomerApiController(CustomerService customerService, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessage> registerACustomer(@RequestBody CustomerRequestModel customerInRequest) {
		
		System.out.println("HERE 0");
		
		CustomerDto customerDto = modelMapper.map(customerInRequest, CustomerDto.class);
		
		
		System.out.println("HERE 1");
		customerService.save(customerDto);
		System.out.println("HERE 2");
		
		ResponseMessage message = new ResponseMessage();
		message.setRequestStatus(RequestStatus.REGISTERED);
		message.setResponseStatus(ResponseStatus.SUCCESS);
		
		return ResponseEntity.created(null).body(message);
	}

}
