package com.example.RamenGo.exceptions;

public class UnauthorisedException extends Exception{

    public UnauthorisedException() {
        super("x-api-key header missing");
    }
}
