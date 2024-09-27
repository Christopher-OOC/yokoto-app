package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="food_items")
public class FoodItem extends Item implements Weightable {

    {
        category = Category.FOOD_ITEM;
    }

    private double weight;
    private Unit unit;

}
