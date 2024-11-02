package com.example.demo.controller;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.request.BusinessRetailRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
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
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createBusiness(
            @PathVariable(value = "customerId") String customerId,
            @RequestPart( value = "data", required = false)  BusinessRetailRequestModel businessRetailModel,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws ResourceAlreadyExistsException {

        BusinessRetailDto businessRetailDto = modelMapper.map(businessRetailModel, BusinessRetailDto.class);

        businessRetailService.registerBusiness(customerId,
                businessRetailDto,
                multipartFile);

        ResponseMessage message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.REGISTERED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

        return ResponseEntity.created(null).body(message);
    }

    @PostMapping("/{businessId}/images")
    public ResponseEntity<?> uploadImage(
            String businessId,
            @RequestPart("file") MultipartFile multipartFile
    ) {

        businessRetailService.uploadImage(businessId, multipartFile);

        ResponseMessage message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.REGISTERED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

        return ResponseEntity.created(null).body(message);
    }

    @PostMapping(path="/{businessId}")
    public ResponseEntity<?> uploadProduct(
            @PathVariable("businessId") String businessId
    ) {



        return null;
    }



}
