package com.example.demo.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
//@Table(name="spices")
@DiscriminatorValue(value = "spice")
public class Spice extends Item {

    {
        category = Category.SPICE;
    }

}
