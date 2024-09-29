package com.example.demo.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
//@Table(name="ingredients")
@DiscriminatorValue(value = "ingre")
public class Ingredient extends Item implements Packetable {

    {
        category = Category.INGREDIENT;
    }
	

}
