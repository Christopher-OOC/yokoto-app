package com.example.demo.servive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.Ingredient;
import com.example.demo.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	public Ingredient getIngredientById(String ingredientId) {
		
		Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
		
		if (ingredient == null) {
			new NoResourceFoundException("No ingredient found!!!");
		}
		
		return ingredient;
	}

}
