package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CateringServiceDto;
import com.example.demo.model.request.CateringServiceRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.CateringServices;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/v1/catering-services")
public class CateringServiceApiController {
	
	private CustomerService customerService;
	
	private CateringServices cateringServices;
	
	private ModelMapper modelMapper;
	
	public CateringServiceApiController(CustomerService customerService, CateringServices cateringServices,
			ModelMapper modelMapper) {
		super();
		this.customerService = customerService;
		this.cateringServices = cateringServices;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/{customerId}")
	public ResponseEntity<ResponseMessage> createCateringService(@PathVariable("customerId") String customerId, 
			@RequestBody CateringServiceRequestModel requestModel) {
		
		CateringServiceDto dto = modelMapper.map(requestModel, CateringServiceDto.class);
		cateringServices.createCateringService(customerId, dto);
		System.out.println("HERE 3s");
		
		ResponseMessage message = new ResponseMessage();
		message.setRequestStatus(RequestStatus.CREATED);
		message.setResponseStatus(ResponseStatus.SUCCESS);
	
		return ResponseEntity.ok(message);
	}
}
