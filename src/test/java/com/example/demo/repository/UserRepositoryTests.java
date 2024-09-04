package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(true)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	final void testCreateUser() {
		
		User user = new User();
		user.setEmail("test@test.com");
		user.setPassword("Chris");
		
		User savedUser = userRepository.save(user);
		
		assertNotNull(savedUser);
	}
}
