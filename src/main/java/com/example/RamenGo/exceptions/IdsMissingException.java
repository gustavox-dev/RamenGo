package com.example.RamenGo.exceptions;

public class IdsMissingException extends RuntimeException {
    public IdsMissingException() {
        super("both brothId and proteinId are required");
    }
}

