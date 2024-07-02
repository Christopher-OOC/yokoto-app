package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	
	private long id;
	
	private String categoryId;
	
	private String categoryName;
	
	private List<Ingredient> ingredients = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDto other = (CategoryDto) obj;
		return id == other.id;
	}
}
