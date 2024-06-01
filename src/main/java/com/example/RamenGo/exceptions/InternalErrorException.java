package com.example.RamenGo.exceptions;

public class InternalErrorException extends Exception{

    public InternalErrorException() {
        super("could not place order");
    }
}
