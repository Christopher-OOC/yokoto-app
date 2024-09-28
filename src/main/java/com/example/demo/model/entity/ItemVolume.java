package com.example.demo.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ItemVolume {

    private double volumeValue;

    @Enumerated(EnumType.STRING)
    private Unit volumeUnit;

}
