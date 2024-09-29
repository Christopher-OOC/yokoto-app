package com.example.demo.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="vegetables")
@DiscriminatorValue(value = "vegs")
public class Vegetable extends Item {

    {
        category = Category.VEGETABLE;
    }
}
