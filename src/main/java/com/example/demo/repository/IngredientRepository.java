package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	
	Ingredient findByIngredientId(String ingredientId);
	
	List<Ingredient> findIngredientsInCategory(String categoryName);

}
