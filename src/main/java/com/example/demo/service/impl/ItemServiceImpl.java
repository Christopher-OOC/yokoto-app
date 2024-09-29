package com.example.demo.service.impl;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.*;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.repository.BusinessRetailRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.ItemService;
import com.example.demo.utils.EntityCheckerUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private BusinessRetailRepository businessRetailRepository;
    private ItemRepository itemRepository;
    private ModelMapper modelMapper;
    private FileService fileService;
    private EntityCheckerUtils entityCheckerUtils;

    public ItemServiceImpl(BusinessRetailRepository businessRetailRepository,
                           ItemRepository itemRepository,
                           ModelMapper modelMapper,
                           FileService fileService,
                           EntityCheckerUtils entityCheckerUtils) {

        this.businessRetailRepository = businessRetailRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
        this.entityCheckerUtils = entityCheckerUtils;
    }

    @Override
    public ItemType<?> uploadItem(String businessId, ItemDto itemDto, MultipartFile[] multipartFiles) {

        //BusinessRetail businessOwner = entityCheckerUtils.checkIfBusinessRetailExists(businessId);

        ItemType<Item> itemType = getItemType(itemDto);
        Item item = itemType.getItem();

        item.setBusinessRetail(null);

        Item saveItem = itemRepository.save(item);

        for (int i = 0; i < multipartFiles.length; i++) {
            MediaPost image = fileService.uploadItemImage(businessId, item.getId(), multipartFiles[i]);
            item.getImages().add(image);
        }

        Item savedItemWithImages = itemRepository.save(item);

        itemType.setItem(savedItemWithImages);

        return itemType;
    }

    @Override
    public List<?> downloadItemImage(String businessId, long itemId, String mediaUrl) {

        entityCheckerUtils.checkIfBusinessRetailExists(businessId);
        entityCheckerUtils.checkIfItemExists(itemId);
        MediaPost mediaPost = entityCheckerUtils.checkIfMediaUrlPathExists(mediaUrl);

        String contentType = mediaPost.getFileType();

        byte[] byteContent = fileService.downloadItemImage(businessId, mediaUrl);

        List<?> list = List.of(contentType, byteContent);


        return list;
    }


    private static ItemType<Item> getItemType(ItemDto itemDto) {

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
                item.setMeatType(itemDto.getMeatType());
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

    private static void setCommonItemProperties(Item item, ItemDto itemDto) {
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setCategory(itemDto.getCategory());
    }
}
