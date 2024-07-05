package com.example.demo.service;

import com.example.demo.model.dto.CustomerDto;

public interface CustomerService {
	
	void save(CustomerDto customerDto);
	
	CustomerDto findByEmail(String email);
	
	CustomerDto findByCustomerId(String customerId);

}
