package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.model.entity.Ingredient;
import com.example.demo.model.entity.Locale;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishDto {
	
	private long id;
	
	private String dishId;
	
	private String dishName;
	
	private List<Ingredient> ingredients = new ArrayList<>();
	
	private Locale locale;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishDto other = (DishDto) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
