package com.pedrofactory.monopoly.api.advice;

import com.pedrofactory.monopoly.exception.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler({ClientAlreadyExistsException.class,UserAlreadyExistsException.class})
    public ResponseEntity<String> clientAlreadyExist(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler({UserNotFoundException.class, ItemNotFoundException.class, InventoryNotFoundException.class,})
    public ResponseEntity<String> userNotFoundException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> entityNotExist(EmptyResultDataAccessException ex){
        return new ResponseEntity<>("Entity not exist", HttpStatus.BAD_REQUEST);
    }
}
