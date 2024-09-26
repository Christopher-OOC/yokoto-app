package com.example.demo.service.impl;

import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	private ModelMapper modelMapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	
	public CategoryDto findByCategoryId(String categoryId) {
		Category category = categoryRepository.findByCategoryId(categoryId);
		
		if (category == null) {
			throw new NoResourceFoundException("No ingredient found!!!");
		}
		
		return modelMapper.map(category, CategoryDto.class);
	}
	
	public Category findByCategoryName(String categoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		
		if (category == null) {
			throw new NoResourceFoundException("No ingredient found!!!");
		}
		
		return category;
	}
	

}
