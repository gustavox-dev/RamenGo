package com.example.RamenGo.service;

import com.example.RamenGo.adapters.ExternalClientAdapter;
import com.example.RamenGo.adapters.OrderDTOAdapter;
import com.example.RamenGo.builder.OrderBuilder;
import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.domain.Order;
import com.example.RamenGo.domain.OrderIdResponse;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.dto.OrderDTO;
import com.example.RamenGo.exceptions.IdsMissingException;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.OrderRepository;
import com.example.RamenGo.response.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {
    
    @InjectMocks
    private OrderService service;
    
    @Mock
    private OrderRepository repository;
    
    @Mock
    private ProteinsService proteinsService;

    @Mock
    private BrothsService brothsService;

    @Mock
    private TokenService tokenService;

    @Mock
    private OrderDTOAdapter orderDTOAdapter;

    @Mock
    private ExternalClientAdapter externalClientAdapter;

    @Autowired
    private OrderBuilder builder;

    @DisplayName("Deve retornar sucesso ao criar um pedido.")
    @Test
    void shouldBeReturnSuccessWhenCreateOrder() throws InternalErrorException, IOException, ItemNotFoundException, InterruptedException, UnauthorisedException {
        String order = "{\n'brothId': '1'\n'proteinId': '1'}";
        OrderDTO orderDTO = new OrderDTO(1L, 1L);
        Proteins protein = builder.mockProtein();
        Broth broth = builder.mockBroth();
        OrderResponse orderResponse = new OrderResponse("12345", "Chasu and Chasu Ramen", "https://tech.redventures.com.br/icons/ramen/ramenChasu.png");

        when(orderDTOAdapter.convertOrderDTOToText(order)).thenReturn(orderDTO);
        when(proteinsService.findProteinById(1L)).thenReturn(protein);
        when(brothsService.findBrothsById(1L)).thenReturn(broth);
//        when(externalClientAdapter.fetchDataFromExternalApi("apiKey")).thenReturn("12345");

        OrderResponse response = service.createOrder("apiKey", order);

        verify(orderDTOAdapter, times(1)).convertOrderDTOToText(order);
        verify(proteinsService, times(1)).findProteinById(1L);
        verify(brothsService, times(1)).findBrothsById(1L);
        verify(externalClientAdapter, times(1)).fetchDataFromExternalApi("apiKey");

        assertNotNull(response);
        assertEquals(orderResponse.getDescription(), response.getDescription());
        assertEquals(orderResponse.getImage(), response.getImage());
    }

    @DisplayName("Deve retornar erro ao criar um pedido sem id da proteina (protein).")
    @Test
    void shouldBeReturnIdsMissingExceptionWhenCreateOrderWithNullProtein() throws JsonProcessingException {
        String order = "{\n'brothId': '1'\n'proteinId': ''}";
        OrderDTO orderDTO = new OrderDTO(1L, null);

        when(orderDTOAdapter.convertOrderDTOToText(order)).thenReturn(orderDTO);
        assertThrows(IdsMissingException.class, () -> service.createOrder("apiKey", order));

    }

    @DisplayName("Deve retornar erro ao criar um pedido sem id do caldo (broth).")
    @Test
    void shouldBeReturnIdsMissingExceptionWhenCreateOrderWithNullBroth() throws JsonProcessingException {
        String order = "{\n'brothId': ''\n'proteinId': '1'}";
        OrderDTO orderDTO = new OrderDTO(null, 1L);

        when(orderDTOAdapter.convertOrderDTOToText(order)).thenReturn(orderDTO);
        assertThrows(IdsMissingException.class, () -> service.createOrder("apiKey", order));
    }

    @DisplayName("Deve retornar erro interno ao criar um pedido.")
    @Test
    void shouldBeReturnInternalErrorExceptionWhenCreateOrder() throws JsonProcessingException {
        String order = "{\n'brothId': '1'\n'proteinId': '1'}";
        OrderDTO orderDTO = new OrderDTO(1L, 1L);

        when(orderDTOAdapter.convertOrderDTOToText(order)).thenReturn(orderDTO);

        assertThrows(InternalErrorException.class, () -> service.createOrder("apiKey", order));

        verify(orderDTOAdapter, times(1)).convertOrderDTOToText(order);
    }

    @DisplayName("Deve retornar sucesso ao buscar todos os pedidos.")
    @Test
    void shouldBeReturnSuccessWhenFindAllOrders() throws InternalErrorException {
        OrderIdResponse orderId = new OrderIdResponse("12345");
        List<Order> orders = Lists.newArrayList(new Order(orderId.getOrderId(), 1L, 1L));
        when(repository.findAll()).thenReturn(orders);

        List<Order> response = service.findAll("apiKey");

        assertNotNull(response);
        orders.forEach(order -> {
            assertEquals(order.getId(), response.get(orders.indexOf(order)).getId());
            assertEquals(order.getBrothId(), response.get(orders.indexOf(order)).getBrothId());
            assertEquals(order.getProteinId(), response.get(orders.indexOf(order)).getProteinId());
        });
        assertEquals(orders.size(), response.size());
    }

    @DisplayName("Deve retornar erro interno ao buscar todos os pedidos.")
    @Test
    void shouldBeReturnInternalErrorWhenFindAllOrder() throws UnauthorisedException {
        doThrow(UnauthorisedException.class).when(tokenService).validateToken("apiKey");

        assertThrows(InternalErrorException.class, () -> service.findAll("apiKey"));
    }
}