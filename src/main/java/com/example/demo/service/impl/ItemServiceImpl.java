package com.example.demo.service.impl;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.CookingOil;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {



    @Override
    public ItemType<?> uploadItem(String businessId, ItemDto itemDto, MultipartFile multipartFile) {

        ItemType<?> itemType = getItemType(itemDto);
    }

    private ItemType<?> getItemType(ItemDto itemDto) {

        if (itemDto.getCategory() == Category.COOKING_OIL) {
            CookingOil item = new CookingOil();
            item.setItemVolume(itemDto.getItemVolume());

        }

    }



}
