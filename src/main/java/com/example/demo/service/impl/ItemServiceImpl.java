package com.example.demo.service.impl;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {



    @Override
    public ItemType<?> uploadItem(String businessId, ItemDto itemDto, MultipartFile multipartFile) {
        return null;
    }



}
