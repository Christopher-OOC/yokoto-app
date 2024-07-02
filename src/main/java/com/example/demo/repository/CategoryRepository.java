package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByCategoryId(String categoryId);
	
	Category findByCategoryName(String categoryName);

}
