package com.example.demo.controller;

import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.model.request.BusinessRetailRequestModel;
import com.example.demo.service.BusinessRetailService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
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

    @PostMapping(value="/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createBusiness(
            @PathVariable(value="customerId") String customerId,
            @RequestPart(name = "data")  BusinessRetailRequestModel businessRetailModel,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile) {

        BusinessRetailDto businessRetailDto = modelMapper.map(businessRetailModel, BusinessRetailDto.class);

        Customer customer = businessRetailService.registerBusiness(customerId,
                businessRetailDto,
                multipartFile);


        return ResponseEntity.created(null).body(customer);
    }
}
