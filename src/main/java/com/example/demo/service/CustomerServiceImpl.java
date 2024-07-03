package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Customer;
import com.example.demo.model.request.CustomerRequestModel;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void save(CustomerRequestModel customerInRequest) {
		Customer customerEntity = modelMapper.map(customerInRequest, Customer.class);
		customerEntity.setPassword(passwordEncoder.encode(customerInRequest.getPassword()));
		
		customerRepository.save(customerEntity);
	}

}
