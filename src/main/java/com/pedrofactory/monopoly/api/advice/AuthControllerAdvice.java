package com.pedrofactory.monopoly.api.advice;

import com.pedrofactory.monopoly.exception.ClientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> entityNotFoundResponse(){
        return new ResponseEntity<>("Client identifier already exists", HttpStatus.CONFLICT);
    }
}
