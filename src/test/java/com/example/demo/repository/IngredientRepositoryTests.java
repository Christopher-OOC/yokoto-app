package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Category;
import com.example.demo.model.Ingredient;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.utils.PublicIdGeneratorUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class IngredientRepositoryTests {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void testCreateIngredient() {
		Category category = categoryRepository.findByCategoryName("Spices");
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Curry");
		ingredient.setIngredientId(PublicIdGeneratorUtils.generatePublicId(30));
		ingredient.setCategory(category);
		
		Ingredient savedIngredient = ingredientRepository.save(ingredient);
		
		assertNotNull(savedIngredient);
		assertEquals(savedIngredient.getCategory().getCategoryName(), category.getCategoryName());
	}
}
