package com.example.demo.service;

import com.example.demo.model.dto.CateringServiceDto;

public interface CateringService {
	
	void createCateringService(String customerId, CateringServiceDto cateringServiceDto);

}
