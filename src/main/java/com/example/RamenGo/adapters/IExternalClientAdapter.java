package com.example.RamenGo.adapters;

import com.example.RamenGo.domain.OrderIdResponse;
import com.example.RamenGo.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface IExternalClientAdapter {
    String fetchDataFromExternalApi(String apiKey) throws IOException, InterruptedException;
    OrderIdResponse convertOrderDTOToText(String body) throws JsonProcessingException;
}
