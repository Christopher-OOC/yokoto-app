package com.example.demo.service;

import com.example.demo.model.dto.DishDto;

public interface DishService {
	
	DishDto findByDishName(String dishName);

}
