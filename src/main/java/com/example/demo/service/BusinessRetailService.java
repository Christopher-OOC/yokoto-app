package com.example.demo.service;

import com.example.demo.model.dto.BusinessRetailDto;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessRetailService {

    void registerBusiness(String customerId,
                          BusinessRetailDto businessRetailDto,
                          MultipartFile multipartFile);

}
