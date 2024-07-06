package com.example.demo.utils;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.constant.ExceptionMessages;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.entity.CatererAssistant;
import com.example.demo.model.entity.Customer;
import com.example.demo.repository.CatererAssistantRepository;
import com.example.demo.repository.CustomerRepository;

@Component
public class EntityCheckerUtils {
	
	private CustomerRepository customerRepository;
	
	private CatererAssistantRepository catererAssistantRepository;
	
	private ModelMapper modelMapper;
	
	public EntityCheckerUtils(CustomerRepository customerRepository, CatererAssistantRepository catererAssistantRepository,
			ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.catererAssistantRepository = catererAssistantRepository;
		this.modelMapper = modelMapper;
	}
	
	public Customer checkIfCustomerExists(String customerId) {
		
		Customer customer = customerRepository.findByCustomerId(customerId);
		
		if (customer == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_CUSTOMER);
		}
		
		return customer;
	}

	public void checkIfCatererAssistantDoesNotExist(String customerId) {
		
		Optional<CatererAssistant> optional = catererAssistantRepository.findById(customerId);
		
		if (optional.isPresent()) {
			throw new NoResourceFoundException(ExceptionMessages.CATERER_ASSISTANT_EXISTS);
		}
	}

}
