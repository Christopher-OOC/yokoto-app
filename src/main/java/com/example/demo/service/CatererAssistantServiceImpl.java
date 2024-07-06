package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CatererAssistantDto;
import com.example.demo.model.entity.CatererAssistant;
import com.example.demo.model.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.utils.EntityCheckerUtils;

@Service
public class CatererAssistantServiceImpl implements CatererAssistantService {
	
	private CustomerRepository customerRepository;
	
	private EntityCheckerUtils entityCheckerUtils;
	
	private ModelMapper modelMapper;
	
	public CatererAssistantServiceImpl(CustomerRepository customerRepository, EntityCheckerUtils entityCheckerUtils,
			ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.entityCheckerUtils = entityCheckerUtils;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createCatererAssistant(String customerId, CatererAssistantDto catererAssistantDto) {
		
		Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);
		// Since customerId is the same as catererAssistantId
		entityCheckerUtils.checkIfCatererAssistantDoesNotExist(customerId);
		
		CatererAssistant catererAssistant = modelMapper.map(catererAssistantDto, CatererAssistant.class);
		catererAssistant.setCaterAssistantId(customerId);
		customer.setCatererAssistant(catererAssistant);
		
		customerRepository.save(customer);
	}

}
