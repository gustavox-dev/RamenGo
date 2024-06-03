package com.example.RamenGo.service;

import com.example.RamenGo.builder.BrothBuilder;
import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.BrothsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BrothsServiceTest {

    @InjectMocks
    private BrothsService service;

    @Mock
    private BrothsRepository repo;

    @Mock
    private TokenService tokenService;

    @Autowired
    private BrothBuilder builder;

    @DisplayName("Deve retornar sucesso quando buscar todos os 'Broths'")
    @Test
    void shouldBeReturnSuccessWhenFindAllBroths() throws UnauthorisedException {
        List<Broth> broths = builder.mockBrothList();

        when(repo.findAll()).thenReturn(broths);
        doNothing().when(tokenService).validateToken("apiKey");

        List<Broth> response = service.findAll("apiKey");

        verify(repo, times(1)).findAll();
        verify(tokenService, times(1)).validateToken("apiKey");

        broths.forEach(broth -> {
            assertEquals(broth.getId(), response.get(broths.indexOf(broth)).getId());
            assertEquals(broth.getName(), response.get(broths.indexOf(broth)).getName());
            assertEquals(broth.getImageInactive(), response.get(broths.indexOf(broth)).getImageInactive());
            assertEquals(broth.getImageActive(), response.get(broths.indexOf(broth)).getImageActive());
            assertEquals(broth.getDescription(), response.get(broths.indexOf(broth)).getDescription());
            assertEquals(broth.getPrice(), response.get(broths.indexOf(broth)).getPrice());
        });

    }

    @DisplayName("Deve retornar falha quando buscar todos os 'Broths'")
    @Test
    void shouldBeReturnFailWhenFindAllBroths() throws UnauthorisedException {
        doThrow(UnauthorisedException.class).when(tokenService).validateToken("apiKey");

        assertThrows(UnauthorisedException.class, () -> service.findAll("apiKey"));

        verify(tokenService, times(1)).validateToken("apiKey");
    }

    @Test
    void shouldBeReturnSuccessWhenFindBrothsById() throws InternalErrorException, ItemNotFoundException {
        Broth mockBroth = builder.mockBroth();

        when(repo.findById(1L)).thenReturn(Optional.of(mockBroth));
        Broth response = service.findBrothsById(mockBroth.getId());

        assertEquals(mockBroth.getId(), response.getId());
        assertEquals(mockBroth.getName(), response.getName());
        assertEquals(mockBroth.getImageInactive(), response.getImageInactive());
        assertEquals(mockBroth.getImageActive(), response.getImageActive());
        assertEquals(mockBroth.getDescription(), response.getDescription());
        assertEquals(mockBroth.getPrice(), response.getPrice());

        verify(repo, times(1)).findById(1L);

    }

    @Test
    void shouldBeReturnItemNotFoundExceptionWhenFindBrothsById() {
        assertThrows(ItemNotFoundException.class, () -> service.findBrothsById(999L));
    }

    @Test
    void shouldBeReturnInternalErrorExceptionWhenFindBrothsById() {
        when(repo.findById(1L)).thenThrow(new RuntimeException("Erro inesperado"));

        assertThrows(InternalErrorException.class, () -> service.findBrothsById(1L));

        verify(repo, times(1)).findById(1L);
    }


}