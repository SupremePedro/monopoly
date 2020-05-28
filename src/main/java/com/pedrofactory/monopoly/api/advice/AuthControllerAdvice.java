package com.pedrofactory.monopoly.api.advice;

import com.pedrofactory.monopoly.exception.ClientAlreadyExistsException;
import com.pedrofactory.monopoly.exception.UserAlreadyExistsException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler({ClientAlreadyExistsException.class,UserAlreadyExistsException.class})
    public ResponseEntity<Exception> clientAlreadyExist(Exception exception){
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Exception> userNotFoundException(UserNotFoundException ex){
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
