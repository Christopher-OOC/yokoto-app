package com.example.demo.service;

import com.example.demo.model.dto.CategoryDto;

public interface CategoryService {

	CategoryDto findByCategoryId(String categoryId);
	
}
