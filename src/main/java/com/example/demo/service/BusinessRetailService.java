package com.example.demo.service;

import com.example.demo.model.dto.BusinessRetailDto;

public interface BusinessRetailService {

    void registerBusiness(String customerId, BusinessRetailDto businessRetailDto);

}
