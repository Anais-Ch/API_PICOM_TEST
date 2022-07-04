package com.hb.PICOM_hibernate.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.PICOM_hibernate.buisness.Country;
import com.hb.PICOM_hibernate.dao.CityDao;
import com.hb.PICOM_hibernate.dao.CountryDao;
import com.hb.PICOM_hibernate.dto.CityDto;
import com.hb.PICOM_hibernate.service.CityService;
import com.hb.PICOM_hibernate.service.CountryService;
import com.hb.PICOM_hibernate.service.impl.CountryServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CityTestController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;



    //TO DO ADD >THE EXCEPTION RESULTS AND OTHER METHODS TO TEST
    @Test
    @Order(1)
    public void testEntry() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/countries/xxx/TEST TEST TEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("xxx"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryName").value("TEST TEST TEST"))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());



    }

    @Test
    @Order(2)
    public void testAddingCity()throws Exception{

        CityDto cityDto = new CityDto();
        cityDto.setCityName("NOSTROMO TEST");
        cityDto.setPostalCode("888888");
        cityDto.setCountryCode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities")
                .content(objectMapper.writeValueAsString(cityDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("31"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").value("NOSTROMO TEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode").value("888888"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCountry.id").value("xxx"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCountry.countryName").value("TEST TEST TEST"))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());



    }
}
