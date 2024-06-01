package com.example.RamenGo.infra;

import com.example.RamenGo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MissingAttributeException.class)
    private ResponseEntity<RestErrorMessage> eventMissingAttributeException(MissingAttributeException exception){
        RestErrorMessage message = new RestErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    private ResponseEntity<RestErrorMessage> eventItemNotFoundException(ItemNotFoundException exception){
        RestErrorMessage message = new RestErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(IdsMissingException.class)
    private ResponseEntity<RestErrorMessage> eventIdsMissingException(IdsMissingException exception){
        RestErrorMessage message = new RestErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(InternalErrorException.class)
    private ResponseEntity<RestErrorMessage> eventInternalErrorException(InternalErrorException exception){
        RestErrorMessage message = new RestErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(UnauthorisedException.class)
    private ResponseEntity<RestErrorMessage> eventInternalErrorException(UnauthorisedException exception){
        RestErrorMessage message = new RestErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
}
