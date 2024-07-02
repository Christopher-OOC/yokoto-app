package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	public UserDto findByUserId(String userId) {
		
		User user = userRepository.findByUserId(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		
		return modelMapper.map(user, UserDto.class);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
}
