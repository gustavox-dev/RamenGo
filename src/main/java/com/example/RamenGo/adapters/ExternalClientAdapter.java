package com.example.RamenGo.adapters;

import com.example.RamenGo.domain.OrderIdResponse;
import com.example.RamenGo.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ExternalClientAdapter implements IExternalClientAdapter{

    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final String URL = "https://api.tech.redventures.com.br/orders/generate-id";
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String fetchDataFromExternalApi(String apiKey) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(URL))
                .header("x-api-key", apiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OrderIdResponse orderIdResponse = convertOrderDTOToText(response.body());

        LOG.info("Success requested with response: " + response.body());
        return orderIdResponse.getOrderId();
    }

    @Override
    public OrderIdResponse convertOrderDTOToText(String body) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, OrderIdResponse.class);
    }
}
