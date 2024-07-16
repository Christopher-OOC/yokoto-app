package com.example.demo.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Dish;
import com.example.demo.repository.DishRepository;
import com.example.demo.utils.PublicIdGeneratorUtils;

@Component
public class DishInitializer {
	
	@Autowired
	private DishRepository dishRepository;
	
	@EventListener
	public void createDish(ApplicationReadyEvent event) {
		
		createDish("Jollof Rice");
		createDish("Ofada Rice");
		createDish("Fried Rice");
		createDish("Pounded Yam");
		
		
	}

	private void createDish(String dishName) {
		Dish dish = dishRepository.findByDishName(dishName);
		
		if (dish == null) {
			
			Dish newDish = new Dish();
			newDish.setDishId(PublicIdGeneratorUtils.generatePublicId(30));
			newDish.setDishName(dishName);
			
			dishRepository.save(newDish);
		}
		
	}

}
