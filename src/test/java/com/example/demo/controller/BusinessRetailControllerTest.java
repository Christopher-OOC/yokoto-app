package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class BusinessRetailControllerTest {

    @DisplayName("Test register business successful only for first time registration")
    @Test
    void testRegister_Return201Created_ForFirstTimeCreation() {



    }


}
