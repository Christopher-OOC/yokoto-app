package com.example.demo.service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;

public interface UserService {
	
	User findByUserId(String userId);
	
	UserDto findByEmail(String email);

}
