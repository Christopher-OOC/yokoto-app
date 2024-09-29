package com.example.demo.service;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.generictype.ItemType;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

    ItemType<?> uploadItem(
            String businessId,
            ItemDto itemDto,
            MultipartFile multipartFile);

}
