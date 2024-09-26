package com.example.demo.service;

import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.request.EventCeremonyRequestModel;

public interface CustomerService {
	
	void createCustomer(CustomerDto customerDto);
	
	CustomerDto findByCustomerId(String customerId);

	void postEventCeremony(String customerId, EventCeremonyRequestModel requestModel);

}
