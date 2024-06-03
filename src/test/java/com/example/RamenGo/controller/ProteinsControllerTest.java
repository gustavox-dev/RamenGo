package com.example.RamenGo.controller;

import com.example.RamenGo.builder.ProteinBuild;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.service.ProteinsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

class ProteinsControllerTest {

    private static final String API_KEY = "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf";

    @InjectMocks
    private ProteinsController controller;

    @Mock
    private ProteinsService service;

    @Autowired
    private ProteinBuild builder;

    @DisplayName("Deve buscar todas as proteinas no banco.")
    @Test
    void shouldBeReturnSuccessWhenFindAllProteins() throws UnauthorisedException {
        List<Proteins> mockProteinsList = builder.mockProteins();
        when(service.findAll(API_KEY)).thenReturn(mockProteinsList);

        List<Proteins> proteinsList = controller.findAll(API_KEY);

        assertEquals(mockProteinsList, proteinsList);
    }
}