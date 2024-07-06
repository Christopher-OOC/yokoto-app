package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CatererAssistantDto;
import com.example.demo.model.request.CatererAssistantRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CatererAssistantService;

@RestController
@RequestMapping("/api/v1/caterer-assistants")
public class CatererAssistantApiController {
	
	private CatererAssistantService catererAssistantService;
	
	private ModelMapper modelMapper;
	
	public CatererAssistantApiController(CatererAssistantService catererAssistantService, ModelMapper modelMapper) {
		this.catererAssistantService = catererAssistantService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/{customerId}")
	public ResponseEntity<ResponseMessage> createCatererAssistant(@PathVariable("customerId") String customerId,
			@RequestBody CatererAssistantRequestModel requestModel) {
		
		CatererAssistantDto dto = modelMapper.map(requestModel, CatererAssistantDto.class);
		
		catererAssistantService.createCatererAssistant(customerId, dto);
		
		ResponseMessage message = new ResponseMessage();
		message.setRequestStatus(RequestStatus.CREATED);
		message.setResponseStatus(ResponseStatus.SUCCESS);
		
		return ResponseEntity.ok(message);
	}
	

}
