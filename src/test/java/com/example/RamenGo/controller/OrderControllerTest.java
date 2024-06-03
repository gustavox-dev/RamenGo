package com.example.RamenGo.controller;

import com.example.RamenGo.adapters.OrderDTOAdapter;
import com.example.RamenGo.domain.Order;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.response.OrderResponse;
import com.example.RamenGo.service.OrderService;
import com.example.RamenGo.service.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderControllerTest {

    private static final String API_KEY = "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf";

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderService service;

    @Mock
    private TokenService tokenService;

    @Mock
    private OrderDTOAdapter orderDTOAdapter;

    @Test
    void findAll() throws InternalErrorException {
        List<Order> orders = controller.findAll(API_KEY);

        assertNotNull(orders);
    }

    @Test
    void createBroths() throws UnauthorisedException, IOException, InternalErrorException, InterruptedException, ItemNotFoundException {
        String order = "{\n'brothId': ''\n'proteinId': '1'}";
        OrderResponse orderResponse = new OrderResponse("12345", "Salt and Chasu Ramen", "https://tech.redventures.com.br/icons/ramen/ramenChasu.png");
        when(service.createOrder(API_KEY, order)).thenReturn(orderResponse);
        OrderResponse response = controller.createBroths(API_KEY, order);

        assertNotNull(response);
    }
}