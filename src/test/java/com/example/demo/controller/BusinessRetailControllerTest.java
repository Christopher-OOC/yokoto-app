package com.example.demo.controller;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.BusinessType;
import com.example.demo.model.entity.Location;
import com.example.demo.model.request.BusinessRetailRequestModel;
import com.example.demo.model.request.LocationRequestModel;
import com.example.demo.model.response.RequestStatus;
import com.example.demo.model.response.ResponseMessage;
import com.example.demo.model.response.ResponseStatus;
import com.example.demo.service.BusinessRetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BusinessRetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BusinessRetailService businessRetailService;

    private LocationRequestModel locationRequestModel;
    private Location location;
    private BusinessRetailRequestModel businessRetailRequestModel;
    private BusinessRetailDto businessRetailDto;
    private ResponseMessage message;
    private String customerId = "i8uyfwehghdjkdhghkjh";

    @BeforeEach
    void setupStubs() {

        locationRequestModel = new LocationRequestModel();
        locationRequestModel.setStreetAddress("17, Bode Olude, Elega, Abeokuta.");
        locationRequestModel.setCityName("Lagos");
        locationRequestModel.setCountry("Nigeria");
        locationRequestModel.setState("Ogun");


        location = new Location();
        location.setStreetAddress("17, Bode Olude, Elega, Abeokuta.");
        location.setCityName("Lagos");
        location.setCountry("Nigeria");
        location.setState("Ogun");

        businessRetailRequestModel =
                new BusinessRetailRequestModel();

        businessRetailRequestModel.setBusinessName("Chris Enterprise");
        businessRetailRequestModel.setBusinessDescription("We care about your health");
        businessRetailRequestModel.setBusinessType(BusinessType.CATERING_SERVICE);
        businessRetailRequestModel.setLocation(locationRequestModel);
        businessRetailRequestModel.setDateCreated(new Date());

        businessRetailDto =
                new BusinessRetailDto();

        businessRetailDto.setBusinessName("Chris Enterprise");
        businessRetailDto.setBusinessDescription("We care about your health");
        businessRetailDto.setBusinessType(BusinessType.CATERING_SERVICE);
        businessRetailDto.setLocation(location);
        businessRetailDto.setDateCreated(new Date());

        message = new ResponseMessage();
        message.setRequestStatus(RequestStatus.REGISTERED);
        message.setResponseStatus(ResponseStatus.SUCCESS);

    }


    @DisplayName("Test register business successful only for first time registration")
    @Test
    void testRegister_Return201Created_ForFirstTimeCreation() throws Exception {

        Mockito.doNothing().when(businessRetailService).registerBusiness(anyString(),
                any(BusinessRetailDto.class), any(MultipartFile.class));

        String bodyContent = objectMapper.writeValueAsString(businessRetailRequestModel);

        mockMvc.perform(
                post("/api/v1/business/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyContent)
        ).andExpect(
                status().isCreated()
        ).andExpectAll(
                jsonPath("$.request_status", is("REGISTERED")),
                jsonPath("$.response_status", is("SUCCESS"))
        ).andDo(print());
    }

    @DisplayName("Test register business failed for another time of registration")
    @Test
    void testRegister_Return400BadRequest_ForExistingCreation() throws Exception {

                //doThrow(ResourceAlreadyExistsException.class)
                doThrow(new ResourceAlreadyExistsException("jdjhhj"))
                        .when(businessRetailService)
                .registerBusiness(
                        anyString(),
                        any(BusinessRetailDto.class),
                        any(MultipartFile.class)
                );

        String bodyContent = objectMapper.writeValueAsString(businessRetailRequestModel);

        mockMvc.perform(
                post("/api/v1/business/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyContent)
        ).andExpect(
                status().isBadRequest()
        ).andDo(print());
    }

}
