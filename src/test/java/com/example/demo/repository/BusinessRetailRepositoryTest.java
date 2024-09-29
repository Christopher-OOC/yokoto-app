package com.example.demo.repository;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.entity.*;
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

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class BusinessRetailRepositoryTest {

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

    private BusinessRetail business;

    private BusinessRetail newBusiness;

    private String email = "test@test.com";

    private String password = "test@123";

    @Autowired
    private BusinessRetailRepository businessRetailRepository;

    @BeforeEach
    void setupStubs() {
        customer = new Customer();
        customer.setId(1);
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

        business = new BusinessRetail();
        business.setBusinessId(PublicIdGeneratorUtils.generatePublicId(30));
        business.setBusinessName("Chris Catering Services");
        business.setBusinessDescription("We cater for people by serving delicious dishes");
        business.setBusinessLogo("ertyuwiqoergtrlhkgn");
        business.setBusinessType(BusinessType.CATERING_SERVICE);
        business.setLocation(location);
        business.setImages(null);
        business.setDateCreated(new Date());

        newBusiness = new BusinessRetail();
        newBusiness.setBusinessId(PublicIdGeneratorUtils.generatePublicId(30));
        newBusiness.setBusinessName("Chris Catering Services");
        newBusiness.setBusinessDescription("We cater for people by serving delicious dishes");
        newBusiness.setBusinessLogo("ertyuwiqoergtrlhkgn");
        newBusiness.setBusinessType(BusinessType.CATERING_SERVICE);
        newBusiness.setLocation(location);
        newBusiness.setImages(null);
        newBusiness.setDateCreated(new Date());
    }

    @DisplayName("Business created successfully with valid data!")
    @Test
    void testCreateBusiness_Success() {

        customer.setBusiness(business);
        business.setCustomer(customer);

        BusinessRetail savedBusiness = businessRetailRepository.save(business);

        assertNotNull(savedBusiness);
        assertEquals(savedBusiness.getCustomer().getCustomerId(), customer.getCustomerId());
    }

    @DisplayName("Cannot create business because you have created one!")
    @Test
    void testCreateBusiness_Failed_becauseOneAAlreadyExists() {


    }


}
