package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findByCategoryId(String categoryId) {
		Category category = categoryRepository.findByCategoryId(categoryId);
		
		if (category == null) {
			throw new NoResourceFoundException("No ingredient found!!!");
		}
		
		return category;
	}
	
	public Category findByCategoryName(String categoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		
		if (category == null) {
			throw new NoResourceFoundException("No ingredient found!!!");
		}
		
		return category;
	}
	

}
