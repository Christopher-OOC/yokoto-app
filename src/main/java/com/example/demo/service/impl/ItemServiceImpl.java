package com.example.demo.service.impl;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.*;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.repository.BusinessRetailRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MediaPostRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.ItemService;
import com.example.demo.utils.EntityCheckerUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private BusinessRetailRepository businessRetailRepository;
    private ItemRepository itemRepository;
    private ModelMapper modelMapper;
    private FileService fileService;
    private EntityCheckerUtils entityCheckerUtils;
    private MediaPostRepository mediaPostRepository;

    public ItemServiceImpl(BusinessRetailRepository businessRetailRepository,
                           ItemRepository itemRepository,
                           ModelMapper modelMapper,
                           FileService fileService,
                           EntityCheckerUtils entityCheckerUtils,
                           MediaPostRepository mediaPostRepository) {

        this.businessRetailRepository = businessRetailRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
        this.entityCheckerUtils = entityCheckerUtils;
        this.mediaPostRepository = mediaPostRepository;
    }

    @Override
    public ItemType<?> uploadItem(String businessId, ItemDto itemDto, MultipartFile[] multipartFiles) {

        BusinessRetail businessOwner = entityCheckerUtils.checkIfBusinessRetailExists(businessId);

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

    @Override
    public Item updateItem(
            String businessId,
            long itemId,
            ItemDto itemDto) {

        entityCheckerUtils.checkIfBusinessRetailExists(businessId);

        Item item = entityCheckerUtils.checkIfItemExists(itemId);


        ItemType<Item> itemType = getItemType(itemDto);
        Item newUpdateItem = itemType.getItem();
        newUpdateItem.setId(item.getId());

        return itemRepository.save(newUpdateItem);
    }

    public Item findById(String businessId, long itemId) {

        entityCheckerUtils.checkIfBusinessRetailExists(businessId);

        Item item = entityCheckerUtils.checkIfItemExists(itemId);

        return item;
    }

    public void updateAnItemImageByImageId(
            String businessId,
            long itemId,
            long imageId,
            MultipartFile multipartFile) {

        entityCheckerUtils.checkIfBusinessRetailExists(businessId);
        Item item = entityCheckerUtils.checkIfItemExists(itemId);
        MediaPost oldImage = entityCheckerUtils.checkIfImageExists(imageId);

        MediaPost newImage = fileService.uploadItemImage(
                businessId,
                itemId,
                multipartFile
        );

        mediaPostRepository.deleteById(imageId);

        List<MediaPost> listImages = item.getImages();
        listImages.remove(oldImage);
        listImages.add(newImage);

        item.setImages(listImages);

        itemRepository.save(item);
   }

    public void updateAllItemImages(String businessId, long itemId, MultipartFile[] multipartFiles) {

        entityCheckerUtils.checkIfBusinessRetailExists(businessId);
        Item item = entityCheckerUtils.checkIfItemExists(itemId);

        List<MediaPost> listNewImages = new ArrayList<>();

        for (int i = 0; i < multipartFiles.length; i++) {
            MediaPost mediaPost = fileService.uploadItemImage(businessId, itemId, multipartFiles[i]);
            listNewImages.add(mediaPost);
        }

        item.setImages(listNewImages);

        itemRepository.save(item);
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
