package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="food_items")
@DiscriminatorValue(value = "food")
public class FoodItem extends Item implements Weightable {

    {
        category = Category.FOOD_ITEM;
    }

    @Enumerated(EnumType.STRING)
    private ItemWeight itemWeight;

}
