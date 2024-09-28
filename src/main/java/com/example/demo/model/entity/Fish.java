package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="fishes")
public class Fish extends Item implements Weightable {

    {
        category = Category.FISH;
    }

    private FishType fishType;

    @Enumerated(EnumType.STRING)
    private ItemWeight itemWeight;

}
