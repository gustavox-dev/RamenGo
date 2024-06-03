package com.example.RamenGo.adapters;

import com.example.RamenGo.exceptions.UnauthorisedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExternalClientAdapterTest {


    @InjectMocks
    private ExternalClientAdapter externalClientAdapter;


    @Test
    void shouldBeReturnConnectExceptionWhenFetchDataFromExternalApi() {
        assertThrows(UnrecognizedPropertyException.class, () -> externalClientAdapter.fetchDataFromExternalApi("apiKey"));
    }
}