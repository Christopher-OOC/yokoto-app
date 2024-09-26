package com.example.demo.controller;

import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.model.request.BusinessRetailRequestModel;
import com.example.demo.service.BusinessRetailService;
import com.example.demo.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/business/")
public class BusinessRetailApiController {

    private CustomerService customerService;
    private BusinessRetailService businessRetailService;
    private ModelMapper modelMapper;
    private FileService fileService;

    public BusinessRetailApiController(
            CustomerService customerService,
            BusinessRetailService businessRetailService,
            ModelMapper modelMapper,
            FileService fileService) {

        this.customerService = customerService;
        this.businessRetailService = businessRetailService;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<?> createBusiness(
            @PathVariable("customerId") String customerId,
            @RequestBody BusinessRetailRequestModel businessRetailModel,
            @RequestParam("file")MultipartFile multipartFile) {

        BusinessRetailDto businessRetailDto = modelMapper.map(businessRetailModel, BusinessRetailDto.class);

        MediaPost mediaPost = fileService.uploadFile(multipartFile);

        businessRetailService.registerBusiness(customerId, businessRetailDto, mediaPost);





    }

}
