package com.example.demo.utils;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.constant.ExceptionMessages;
import com.example.demo.model.entity.CatererAssistant;
import com.example.demo.model.entity.Ceremony;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Dish;
import com.example.demo.repository.CatererAssistantRepository;
import com.example.demo.repository.CeremonyRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DishRepository;

@Component
public class EntityCheckerUtils {
	
	private CustomerRepository customerRepository;
	
	private CatererAssistantRepository catererAssistantRepository;
	
	private CeremonyRepository ceremonyRepository;
	
	private DishRepository dishRepository;
	
	private ModelMapper modelMapper;
	
	public EntityCheckerUtils(CustomerRepository customerRepository, CatererAssistantRepository catererAssistantRepository,
			CeremonyRepository ceremonyRepository, DishRepository dishRepository,
			ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.catererAssistantRepository = catererAssistantRepository;
		this.ceremonyRepository = ceremonyRepository;
		this.dishRepository = dishRepository;
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

}
