package com.example.demo.service;

import com.example.demo.model.dto.CateringServiceDto;

public interface CateringServices {
	
	void createCateringService(String customerId, CateringServiceDto cateringServiceDto);

}
