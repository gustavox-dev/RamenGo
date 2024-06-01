package com.example.RamenGo.service;

import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.filter.ApiKeyFilter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TokenService {

    private static final String API_KEY = ApiKeyFilter.API_KEY;

    public void validateToken(String apiKey) throws UnauthorisedException {
        if(!Objects.equals(apiKey, API_KEY)){
            throw new UnauthorisedException();
        }
    }
}
