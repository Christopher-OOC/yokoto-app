package com.example.demo.utils;

import java.util.Optional;

import com.example.demo.model.entity.BusinessRetail;
import com.example.demo.repository.BusinessRetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.constant.ExceptionMessages;
import com.example.demo.model.entity.Ceremony;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Dish;
import com.example.demo.repository.CeremonyRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DishRepository;

@Component
public class EntityCheckerUtils {
	
	private CustomerRepository customerRepository;
	private CeremonyRepository ceremonyRepository;
	private DishRepository dishRepository;
	private ModelMapper modelMapper;
	private BusinessRetailRepository businessRetailRepository;

	
	public EntityCheckerUtils(
			CustomerRepository customerRepository,
			CeremonyRepository ceremonyRepository,
			DishRepository dishRepository,
			ModelMapper modelMapper,
			BusinessRetailRepository businessRetailRepository) {

		this.customerRepository = customerRepository;
		this.ceremonyRepository = ceremonyRepository;
		this.dishRepository = dishRepository;
		this.modelMapper = modelMapper;
		this.businessRetailRepository = businessRetailRepository;
	}
	
	public Customer checkIfCustomerExists(String customerId) {
		
		Customer customer = customerRepository.findByCustomerId(customerId);
		
		if (customer == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_CUSTOMER);
		}
		
		return customer;
	}

	public Ceremony checkIfCeremonyExistsByName(String ceremonyName) {
		Ceremony ceremony = ceremonyRepository.findByCeremonyName(ceremonyName);
		
		if (ceremony == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_CEREMONY);
		}
		
		return ceremony;
	}

	public Dish checkIfDishExists(String dishId) {
		
		Dish dish = dishRepository.findByDishId(dishId);
		
		if (dish == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_DISH);
		}
		
		return dish;
	}

	public BusinessRetail checkIfBusinessRetailExists(String businessId) {

		BusinessRetail businessRetail = businessRetailRepository.findByBusinessId(businessId);

		if (businessRetail == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_BUSINESS_RETAIIL);
		}

		return businessRetail;
	}

}
