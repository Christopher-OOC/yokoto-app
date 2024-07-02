package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.IngredientDto;
import com.example.demo.model.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	private IngredientRepository ingredientRepository;
	
	private ModelMapper modelMapper;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper modelMapper) {
		this.ingredientRepository = ingredientRepository;
		this.modelMapper = modelMapper;
	}
	
	public IngredientDto findIngredientById(String ingredientId) {
		
		Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
		
		if (ingredient == null) {
			new NoResourceFoundException("No ingredient found!!!");
		}
		
		return modelMapper.map(ingredient, IngredientDto.class);
	}

}
