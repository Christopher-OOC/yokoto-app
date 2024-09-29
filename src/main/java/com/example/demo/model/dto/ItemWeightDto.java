package com.example.demo.model.dto;

import com.example.demo.model.entity.Unit;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemWeightDto {

    private double weightValue;

    private Unit weightUnit;

}
