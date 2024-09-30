package com.example.demo.utils;

import com.example.demo.model.entity.*;
import com.example.demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.constant.ExceptionMessages;

import java.util.Optional;

@Component
public class EntityCheckerUtils {
	
	private CustomerRepository customerRepository;
	private CeremonyRepository ceremonyRepository;
	private DishRepository dishRepository;
	private ModelMapper modelMapper;
	private BusinessRetailRepository businessRetailRepository;
	private ItemRepository itemRepository;
	private MediaPostRepository mediaPostRepository;

	
	public EntityCheckerUtils(
			CustomerRepository customerRepository,
			CeremonyRepository ceremonyRepository,
			DishRepository dishRepository,
			ModelMapper modelMapper,
			BusinessRetailRepository businessRetailRepository,
			ItemRepository itemRepository,
			MediaPostRepository mediaPostRepository) {

		this.customerRepository = customerRepository;
		this.ceremonyRepository = ceremonyRepository;
		this.dishRepository = dishRepository;
		this.modelMapper = modelMapper;
		this.businessRetailRepository = businessRetailRepository;
		this.itemRepository = itemRepository;
		this.mediaPostRepository = mediaPostRepository;

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

	public Item checkIfItemExists(Long itemId) {

		Optional<Item> optional = itemRepository.findById(itemId);

		if (optional.isEmpty()) {
			throw new NoResourceFoundException(ExceptionMessages.NO_ITEM);
		}

		return optional.get();
	}

	public MediaPost checkIfMediaUrlPathExists(String mediaUrl) {

		MediaPost mediaPost = mediaPostRepository.findByMediaUrl(mediaUrl);

		if (mediaPost == null) {
			throw new NoResourceFoundException(ExceptionMessages.NO_MEDIA);
		}

		return mediaPost;
	}

	public MediaPost checkIfImageExists(Long imageId) {

		Optional<MediaPost> optional = mediaPostRepository.findById(imageId);

		if (optional.isEmpty()) {
			throw new NoResourceFoundException(ExceptionMessages.NO_MEDIA);
		}

		return optional.get();
	}

}
