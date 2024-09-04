package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(true)
public class CustomerRepositoryTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	void testCreateSuccess() {
		
		Customer customer = new Customer();
		customer.setEmail("test@test.com");
		customer.setFirstName("Christopher");
		customer.setLastName("Olojede");
		customer.setDateOfBirth(new Date());
		customer.setCustomerId("kuyjjjskdhsadiadlpoi");
		
		Location location = new Location();
		location.setCityName("Lagos");
		location.setCountry("Nigeria");
		location.setState("Ogun");
		
		customer.setLocation(location);
		
		User user = new User();
		user.setEmail(customer.getEmail());
		user.setEmailVerificationStatus(false);
		user.setEmailVerificationToken("kuytyyrtcvbnm");
		//user.setPassword(passwordEncoder.encode("jhddjmfdjhjhsdfjhfdhhf"));
		user.setPasswordResetToken(null);
		user.setRoles(Arrays.asList(roleRepository.findByRoleName(Roles.CUSTOMER.name())));
		
		customer.setUser(user);
		
		Customer savedCustomer = customerRepository.save(customer);
		
		assertNotNull(savedCustomer);
	}

}
