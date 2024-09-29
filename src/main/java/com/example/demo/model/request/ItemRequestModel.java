package com.example.demo.model.request;

import com.example.demo.model.dto.ItemVolumeDto;
import com.example.demo.model.dto.ItemWeightDto;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.MeatType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemRequestModel {

    private String name;
    private double price;
    private Category category;
    private MeatType meatType;
    private ItemWeightDto itemWeight;
    private ItemVolumeDto itemVolume;

}
