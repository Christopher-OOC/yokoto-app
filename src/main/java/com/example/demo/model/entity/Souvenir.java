package com.example.demo.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="souvenirs")
@DiscriminatorValue(value = "souvenirs")
public class Souvenir extends Item {

    {
        category = Category.SOUVENIR;
    }

}
