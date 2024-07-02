package com.example.demo.service;

import com.example.demo.model.dto.IngredientDto;

public interface IngredientService {

	IngredientDto findIngredientById(String ingredientId);
	
}
