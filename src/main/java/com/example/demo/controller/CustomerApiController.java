package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CateringServiceDto;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.request.CateringServiceRequestModel;
import com.example.demo.model.request.CustomerRequestModel;
import com.example.demo.model.request.CeremonyRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.CateringServices;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerApiController {
	
	private CustomerService customerService;
	
	private CateringServices cateringServices;
	
	private ModelMapper modelMapper;
	
	public CustomerApiController(CustomerService customerService, CateringServices cateringServices, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.cateringServices = cateringServices;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessage> registerACustomer(@RequestBody CustomerRequestModel customerInRequest) {
				
		CustomerDto customerDto = modelMapper.map(customerInRequest, CustomerDto.class);
		
		customerService.save(customerDto);
		
		ResponseMessage message = new ResponseMessage();
		message.setRequestStatus(RequestStatus.REGISTERED);
		message.setResponseStatus(ResponseStatus.SUCCESS);
		
		return ResponseEntity.created(null).body(message);
	}
	
	@PostMapping("/{events}")
	public ResponseEntity<?> createEventCeremony(@RequestBody CeremonyRequestModel requestModel) {
		
		
		return ResponseEntity.created(null).body(null);
	}

}
