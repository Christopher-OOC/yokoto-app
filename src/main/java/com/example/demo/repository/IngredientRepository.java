package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	
	Ingredient findByIngredientId(String ingredientId);
	
	@Query("""
			SELECT ingre FROM Ingredient ingre 
			WHERE ingre.category.categoryName = ?1
			""")
	List<Ingredient> findIngredientsInCategory(String categoryName);

}
