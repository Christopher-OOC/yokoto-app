package com.example.demo.controller;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Item;
import com.example.demo.model.generictype.ItemType;
import com.example.demo.model.request.ItemRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PutMapping(value = "/{businessId}")
    public ResponseEntity<?>  updateItem(
            @PathVariable("businessId") String businessId,
            @PathVariable("itemId") long itemId,
            @RequestBody ItemRequestModel itemRequestModel
    ) {

        ItemDto itemDto = modelMapper.map(itemRequestModel, ItemDto.class);

        Item updateItem = itemService.updateItem(businessId, itemId, itemDto);

        return ResponseEntity.ok(updateItem);
    }

    @PutMapping(value = "/{businessId}/{itemId}/imageId", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAnItemImageByImageId(
            @PathVariable("businessId") String businessId,
            @PathVariable("itemId") long itemId,
            @PathVariable("imageId") long imageId,
            @RequestPart("file") MultipartFile multipartFile) {

        itemService.updateAnItemImageByImageId(businessId, itemId, imageId, multipartFile);

        ResponseMessage message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.UPDATED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

        return ResponseEntity.ok(message);
    }

    @PutMapping(value = "/{businessId}/{itemId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAllItemImages(
            @PathVariable("businessId") String businessId,
            @PathVariable("itemId") long itemId,
            @RequestPart("file") MultipartFile[] multipartFiles) {

        itemService.updateAllItemImages(businessId, itemId, multipartFiles);

        ResponseMessage message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.UPDATED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{businessId}/{itemId}")
    public ResponseEntity<?> getItemById(
            @PathVariable("businessId") String businessId,
            @PathVariable("itemId") long itemId
    ) {

        Item item = itemService.findById(businessId, itemId);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/{businessId}/{itemId}/{mediaUrl}")
    public ResponseEntity<?> downloadMediaByUrl(
            String businessId,
            long itemId,
            String mediaUrl
    ) {

        List<?> listContentTypeAndData = itemService.downloadItemImage(businessId, itemId, mediaUrl);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf((String) listContentTypeAndData.get(0)))
                .body((byte[]) listContentTypeAndData.get(1));
    }
}
