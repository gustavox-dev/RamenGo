package com.example.RamenGo.service;

import com.example.RamenGo.builder.ProteinBuild;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.ProteinsRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class ProteinsServiceTest {

    @InjectMocks
    private ProteinsService service;

    @Mock
    private ProteinsRepository repository;

    @Autowired
    private ProteinBuild build;

    @Mock
    private TokenService tokenService;


    @DisplayName("Deve retornar sucesso quando buscar todos os 'Proteins'")
    @Test
    void shouldBeReturnSuccessWhenFindAllProteins() throws UnauthorisedException {
        List<Proteins> proteinsList = build.mockProteins();

        when(repository.findAll()).thenReturn(proteinsList);

        List<Proteins> resposta = service.findAll("apiKey");

        assertNotNull(resposta);
        proteinsList.forEach(proteins -> {
            assertEquals(proteins.getId(), resposta.get(proteinsList.indexOf(proteins)).getId());
            assertEquals(proteins.getImageInactive(), resposta.get(proteinsList.indexOf(proteins)).getImageInactive());
            assertEquals(proteins.getImageActive(), resposta.get(proteinsList.indexOf(proteins)).getImageActive());
            assertEquals(proteins.getName(), resposta.get(proteinsList.indexOf(proteins)).getName());
            assertEquals(proteins.getDescription(), resposta.get(proteinsList.indexOf(proteins)).getDescription());
            assertEquals(proteins.getPrice(), resposta.get(proteinsList.indexOf(proteins)).getPrice());
        });

    }

    @DisplayName("Deve retornar erro quando buscar todos os 'Proteins'")
    @Test
    void shouldBeReturnFailWhenFindAllProteins() throws UnauthorisedException {

        doThrow(UnauthorisedException.class).when(tokenService).validateToken("apiKey");

        assertThrows(UnauthorisedException.class, () -> service.findAll("apiKey"));
    }

    @Test
    void shouldBeReturnSuccessWhenFindProteinsById() throws ItemNotFoundException, InternalErrorException {
        Proteins proteins = build.mockProtein();
        
        when(repository.findById(1L)).thenReturn(Optional.of(proteins));
        
        Proteins response = service.findProteinById(1L);

        assertEquals(proteins.getId(), response.getId());
        assertEquals(proteins.getName(), response.getName());
        assertEquals(proteins.getImageInactive(), response.getImageInactive());
        assertEquals(proteins.getImageActive(), response.getImageActive());
        assertEquals(proteins.getDescription(), response.getDescription());
        assertEquals(proteins.getPrice(), response.getPrice());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldBeReturnItemNotFoundExceptionWhenFindProteinsById() {
        assertThrows(ItemNotFoundException.class, () -> service.findProteinById(999L));
    }

    @Test
    void shouldBeReturnInternalErrorExceptionWhenFindProteinsById() {
        when(repository.findById(1L)).thenThrow(new RuntimeException("Erro inesperado"));

        assertThrows(InternalErrorException.class, () -> service.findProteinById(1L));

        verify(repository, times(1)).findById(1L);
    }
}