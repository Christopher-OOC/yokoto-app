package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name="meats")
public class Meat extends Item implements Weightable {

    {
        category = Category.MEAT;
    }

    private MeatType meatType;

    @Enumerated(EnumType.STRING)
    private ItemWeight itemWeight;

}
