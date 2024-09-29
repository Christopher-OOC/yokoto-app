package com.example.demo.model.dto;

import com.example.demo.model.entity.BusinessRetail;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.ItemVolume;
import com.example.demo.model.entity.ItemWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private double price;
    private Category category;
    private ItemWeightDto itemWeight;
    private ItemVolumeDto itemVolume;
    private BusinessRetailDto businessRetail;
    private String businessId;
    private ModelMapper modelMapper;

    {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public ItemWeight convertAttributeItemWeightDtoToEntity() {

        return modelMapper.map(itemWeight, ItemWeight.class);
    }

    public ItemVolume convertAttributeItemVolumeDtoToEntity() {

        return modelMapper.map(itemVolume, ItemVolume.class);
    }

    public BusinessRetail convertAttributeBusinessRetailDtoToEntity() {

        return modelMapper.map(businessRetail, BusinessRetail.class);
    }
}
