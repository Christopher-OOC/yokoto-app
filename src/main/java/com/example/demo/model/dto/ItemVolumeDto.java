package com.example.demo.model.dto;

import com.example.demo.model.entity.Unit;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemVolumeDto {

    private double volumeValue;

    private Unit volumeUnit;

}
