package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
//@Table(name="fishes")
@DiscriminatorValue(value = "fishes")
public class Fish extends Item implements Weightable {

    {
        category = Category.FISH;
    }

    @Enumerated(EnumType.STRING)
    private FishType fishType;

    private ItemWeight itemWeight;

}
