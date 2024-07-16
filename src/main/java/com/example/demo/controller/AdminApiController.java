package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.AdminDto;
import com.example.demo.model.request.AdminRequestModel;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminApiController {
	
	private ModelMapper modelMapper;
	
	public AdminApiController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<?> createAdmin(@RequestBody AdminRequestModel requestModel) {
		AdminDto dto = modelMapper.map(requestModel, AdminDto.class);
		
		return null;
	}

}
