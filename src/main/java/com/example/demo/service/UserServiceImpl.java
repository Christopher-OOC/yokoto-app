package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	
	public void saveUser(User user) {
		userRepository.save(user);
	}


	@Override
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserDto findByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new NoResourceFoundException("No user found !!!");
		}
		
		return modelMapper.map(user, UserDto.class);
	}
}
