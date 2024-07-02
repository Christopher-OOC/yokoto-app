package com.example.demo.model.dto;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {

	private long id;
	
	private String ingredientId;
	
	private String ingredientName;
	
	private CategoryDto category;

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
		IngredientDto other = (IngredientDto) obj;
		return id == other.id;
	}
}
