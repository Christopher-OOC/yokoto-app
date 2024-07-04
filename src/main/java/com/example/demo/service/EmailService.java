package com.example.demo.service;

import com.example.demo.model.entity.User;

public interface EmailService {
	
	void sendEmailVerification(User user);

}
