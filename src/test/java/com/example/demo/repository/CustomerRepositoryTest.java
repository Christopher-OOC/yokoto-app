package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;

import com.example.demo.utils.PublicIdGeneratorUtils;
import com.example.demo.utils.TokenGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(true)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	private Customer customer;

	private String customerId = PublicIdGeneratorUtils.generatePublicId(30);

	private Location location;

	private User user;

	private String email = "test@test.com";

	private String password = "test@123";

	@BeforeEach
	void setupStubs() {

		customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setFirstName("Christopher");
		customer.setLastName("Olojede");
		customer.setDateOfBirth(new Date());

		Location location = new Location();
		location.setStreetAddress("17, Bode Olude, Elega, Abeokuta.");
		location.setCityName("Lagos");
		location.setCountry("Nigeria");
		location.setState("Ogun");

		customer.setLocation(location);

		user = new User();
		user.setCustomerId(customer.getCustomerId());
		user.setEmail(email);
		user.setEmailVerificationStatus(true);
		user.setEmailVerificationToken(TokenGenerators.generateEmailVerificationToken(email));
		user.setPassword(password);
		user.setPasswordResetToken(null);

		user.setRoles(Arrays.asList(roleRepository.findByRoleName(Roles.CUSTOMER.name())));
	}

	@DisplayName("Customer created successfully with valid data!")
	@Test
	void testCreate_Success() {
		
		Customer savedCustomer = customerRepository.save(customer);
		User savedUser = userRepository.save(user);
		
		assertNotNull(savedCustomer);
		assertNotNull(savedCustomer);
		assertEquals(savedCustomer.getCustomerId(), savedUser.getCustomerId(), "They must be equal");
		assertEquals(savedUser.getRoles().get(0).getRoleName(), Roles.CUSTOMER.name());
	}



}
