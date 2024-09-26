package com.example.demo.service.impl;

import com.example.demo.service.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.DishDto;
import com.example.demo.model.entity.Dish;
import com.example.demo.repository.DishRepository;

@Service
public class DishServiceImpl implements DishService {

	private DishRepository dishRepository;
	
	private ModelMapper modelMapper;
	
	public DishServiceImpl(DishRepository dishRepository, ModelMapper modelMapper) {
		this.dishRepository = dishRepository;
		this.modelMapper = modelMapper;
	}
	
	public DishDto findByDishName(String dishName) {
		Dish dish = dishRepository.findByDishName(dishName);
		
		if (dish == null) {
			throw new NoResourceFoundException("No such dish found");
		}
		
		return modelMapper.map(dish, DishDto.class);
	}

}
