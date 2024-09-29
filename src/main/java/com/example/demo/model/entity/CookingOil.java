package com.example.demo.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
//@Table(name="cooking_oils")
@DiscriminatorValue(value = "oils")
public class CookingOil extends Item implements Volumetric {

    {
        category = Category.COOKING_OIL;
    }


    private ItemVolume itemVolume;

}
