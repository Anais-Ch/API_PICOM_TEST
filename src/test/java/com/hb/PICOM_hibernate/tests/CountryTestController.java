package com.hb.PICOM_hibernate.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CountryTestController {

    // to add  component need to use @Autowire in test
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    //TO DO ADD >THE EXCEPTION RESULTS AND OTHER METHODS TO TEST
    @Test
    @Order(1)
    public void testAddingCountry() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/countries/tes/TEST SAMPLE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("tes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryName").value("TEST SAMPLE"))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @Order(2)
    public void testGetCountryById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/countries/tes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("tes"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    public void testDeleteCountry() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/countries/tes"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
