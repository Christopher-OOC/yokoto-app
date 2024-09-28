package com.example.demo.controller;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.model.request.CustomerRequestModel;
import com.example.demo.model.request.LocationRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.PublicIdGeneratorUtils;
import com.example.demo.utils.TokenGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;
    private CustomerDto customerDto;
    private CustomerRequestModel customerRequestModel;
    private String customerId = PublicIdGeneratorUtils.generatePublicId(30);
    private LocationRequestModel location;
    private User user;
    private String email = "test@test.com";
    private String password = "test@123";
    private ResponseMessage message;


    @BeforeEach
    void setupStubs() {
        customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstName("Christopher");
        customer.setLastName("Olojede");
        customer.setDateOfBirth(new Date());

        customerDto = new CustomerDto();
        customerDto.setFirstName("Christopher");
        customerDto.setLastName("Olojede");
        customerDto.setDateOfBirth(new Date());

        customerRequestModel = new CustomerRequestModel();
        customerRequestModel.setFirstName("Christopher");
        customerRequestModel.setLastName("Olojede");
        customerRequestModel.setEmail(email);
        customerRequestModel.setPassword(password);
        customerRequestModel.setDateOfBirth(new Date());

        location = new LocationRequestModel();
        location.setStreetAddress("17, Bode Olude, Elega, Abeokuta.");
        location.setCityName("Lagos");
        location.setCountry("Nigeria");
        location.setState("Ogun");

        customerRequestModel.setLocation(location);

        user = new User();
        user.setCustomerId(customer.getCustomerId());
        user.setEmail(email);
        user.setEmailVerificationStatus(true);
        user.setEmailVerificationToken(TokenGenerators.generateEmailVerificationToken(email));
        user.setPassword(password);
        user.setPasswordResetToken(null);

        Role role = new Role();
        role.setId(111);
        role.setRoleName(Roles.CUSTOMER.name());

        user.setRoles(List.of(role));

        message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.REGISTERED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

    }

    @DisplayName("Test register customer successful with valid data")
    @Test
    void testRegister_ReturnStatus201Created_withValidData() throws Exception {

        Mockito.doNothing().when(customerService).createCustomer(customerDto);

        String bodyContent = objectMapper.writeValueAsString(customerRequestModel);

        mockMvc
                .perform(
                     post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyContent)
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        jsonPath("$.request_status", is("REGISTERED")),
                        jsonPath("$.response_status", is("SUCCESS"))
                );
    }

    @DisplayName("Test Register customer failed with invalid password length")
    @Test
    void testRegister_ReturnStatus400BadRequest_withInvalidPasswordLength() throws Exception {

        customerRequestModel.setPassword("chris");

        Mockito.doNothing().when(customerService).createCustomer(customerDto);

        String bodyContent = objectMapper.writeValueAsString(customerRequestModel);

        mockMvc
                .perform(
                        post("/api/v1/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bodyContent)
                )
                .andExpect(
                        status().isBadRequest()
                )
                .andExpect(jsonPath("$.messages[0]", is("Password must be between 8 to 20 characters")))
                .andDo(print());
    }
}
