package com.example.demo.service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	
	User findByUserId(String userId);
	
	UserDto findByEmail(String email);

}
