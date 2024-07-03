package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.entity.Authority;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.utils.PublicIdGeneratorUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	final void testCreateUser() {
		
		Authority authority1 = new Authority();
		authority1.setAuthorityName("CREATE_INGREDIENT");
		
		Authority authority2 = new Authority();
		authority2.setAuthorityName("DELETE_INGREDIENT");
		
		Authority authority3 = new Authority();
		authority3.setAuthorityName("UPDATE_INGREDIENT");
		
		Role role1 = new Role();
		role1.setRoleName("ROLE_ADMIN");
		role1.setAuthorities(Arrays.asList(authority1, authority2, authority3));
		
		User user = new User();
		user.setEmail("test@test.com");
		user.setRoles(Arrays.asList(role1));
		
		User savedUser = userRepository.save(user);
		
		assertNotNull(savedUser);
	}
}
