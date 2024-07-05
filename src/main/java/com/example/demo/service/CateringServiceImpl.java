package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CateringServiceDto;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.utils.EntityCheckerUtils;

@Service
public class CateringServiceImpl implements CateringService {
	
	private CustomerRepository customerRepository;
	
	private EntityCheckerUtils entityCheckerUtils;
	
	private ModelMapper modelMapper;
	
	public CateringServiceImpl(CustomerRepository customerRepository, EntityCheckerUtils entityCheckerUtils, 
			ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.entityCheckerUtils = entityCheckerUtils;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createCateringService(String customerId, CateringServiceDto cateringServiceDto) {
		
		
		
		
	}

}
