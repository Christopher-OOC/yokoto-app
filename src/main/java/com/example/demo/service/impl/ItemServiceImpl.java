package com.example.demo.service.impl;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.*;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.repository.BusinessRetailRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.BusinessRetailService;
import com.example.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {

    private BusinessRetailRepository businessRetailRepository;

    private ItemRepository itemRepository;

    private ModelMapper modelMapper;




    @Override
    public ItemType<?> uploadItem(String businessId, ItemDto itemDto, MultipartFile multipartFile) {

        ItemType<Item> itemType = getItemType(itemDto);







    }

    private ItemType<Item> getItemType(ItemDto itemDto) {

        ItemType<Item> itemType = new ItemType<>();

        switch (itemDto.getCategory()) {

            case COOKING_OIL : {
                CookingOil item = new CookingOil();
                setCommonItemProperties(item, itemDto);
                item.setItemVolume(itemDto.convertAttributeItemVolumeDtoToEntity());

                itemType.setItem(item);
            }
            break;
            case FISH : {
                Fish item = new Fish();
                setCommonItemProperties(item, itemDto);
                item.setItemWeight(itemDto.convertAttributeItemWeightDtoToEntity());
                itemType.setItem(item);
            }
            break;
            case FOOD_ITEM : {
                FoodItem item = new FoodItem();
                setCommonItemProperties(item, itemDto);
                item.setItemWeight(itemDto.convertAttributeItemWeightDtoToEntity());
                itemType.setItem(item);
            }
            break;
            case INGREDIENT : {
                Ingredient item = new Ingredient();
                setCommonItemProperties(item, itemDto);
                itemType.setItem(item);

            }
            break;
            case MEAT : {
                Meat item = new Meat();
                setCommonItemProperties(item, itemDto);
                item.setItemWeight(itemDto.convertAttributeItemWeightDtoToEntity());
                itemType.setItem(item);
            }
            break;
            case PACKAGING_ITEM : {
                PackagingItem item = new PackagingItem();
                setCommonItemProperties(item, itemDto);
                itemType.setItem(item);
            }
            break;
            case SOUVENIR : {
                Souvenir item = new Souvenir();
                setCommonItemProperties(item, itemDto);
                itemType.setItem(item);
            }
            break;
            case SPICE : {
                Spice item = new Spice();
                setCommonItemProperties(item, itemDto);
                itemType.setItem(item);
            }
            break;
            case VEGETABLE : {
                Vegetable item = new Vegetable();
                setCommonItemProperties(item, itemDto);
                itemType.setItem(item);

            }
            break;
            default : {
                System.out.println("Error");
            }
        }

        return itemType;
    }

    private void setCommonItemProperties(Item item, ItemDto itemDto) {
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setCategory(Category.COOKING_OIL);
    }



}
