package com.example.RamenGo.adapters;

import com.example.RamenGo.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IOrderDTOAdapter {
    OrderDTO convertOrderDTOToText(String body) throws JsonProcessingException;
}
