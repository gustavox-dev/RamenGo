package com.example.RamenGo.adapters;

import com.example.RamenGo.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOAdapter implements IOrderDTOAdapter {
    @Override
    public OrderDTO convertOrderDTOToText(String body) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, OrderDTO.class);
    }
}
