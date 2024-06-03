package com.example.RamenGo.controller;

import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class BrothsControllerTest {

    private static final String API_KEY = "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf";
    @Autowired
    private BrothsController controller;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @BeforeEach
    void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findAllBroths() throws UnauthorisedException {
        List<Broth> broths = controller.findAll(API_KEY);

        assertNotNull(broths);
    }
}