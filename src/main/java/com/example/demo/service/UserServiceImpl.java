package com.example.demo.service;

import com.example.demo.model.entity.Authority;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("No such user!");
		}

		Collection<SimpleGrantedAuthority> userRolesAndAuthorities = new ArrayList<>();

		user.getRoles().forEach(role -> {
			userRolesAndAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			userRolesAndAuthorities.addAll(
				role.getAuthorities()
						.stream()
						.map(authority -> authority.getAuthorityName())
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()));
		});

		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), userRolesAndAuthorities);
	}
}
