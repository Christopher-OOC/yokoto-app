package com.example.demo.service;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Item;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.model.request.ItemRequestModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    ItemType<?> uploadItem(
            String businessId,
            ItemDto itemDto,
            MultipartFile[] multipartFiles);

    Item updateItem(String businessId, long itemId, ItemDto itemDto);

    Item findById(String businessId, long itemId);

    void updateAnItemImageByImageId(String businessId, long itemId, long imageId, MultipartFile multipartFile);

    List<?> downloadItemImage(
            String businessId,
            long itemId,
            String mediaUrl);

}
