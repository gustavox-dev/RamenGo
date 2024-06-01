package com.example.RamenGo.exceptions;

public class MissingAttributeException extends Exception{

    private String attribute;
    public MissingAttributeException() {
        super("");
    }

    public MissingAttributeException(String attribute) {
        super("The "+ attribute + " attribute cannot be null");
    }
}
