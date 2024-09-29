package com.example.demo.controller;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.model.request.ItemRequestModel;
import com.example.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/items")
public class ItemApiController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @PostMapping(value="/{businessId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadItem(
            @PathVariable("businessId") String businessId,
            @RequestPart(value = "data") ItemRequestModel itemRequestModel,
            @RequestParam(value = "file") MultipartFile[] multipartFiles
            ) {

        ItemDto itemDto = modelMapper.map(itemRequestModel, ItemDto.class);

        ItemType<?> itemType = itemService.uploadItem(businessId, itemDto, multipartFiles);

        return ResponseEntity.created(null).body(itemType.getItem());
    }
}
