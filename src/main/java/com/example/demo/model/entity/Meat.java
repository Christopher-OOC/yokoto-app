package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="meats")
@DiscriminatorValue(value = "meats")
public class Meat extends Item implements Weightable {

    {
        category = Category.MEAT;
    }

    @Enumerated(EnumType.STRING)
    private MeatType meatType;

    @Enumerated(EnumType.STRING)
    private ItemWeight itemWeight;

}
