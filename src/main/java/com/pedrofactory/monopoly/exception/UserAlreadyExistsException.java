package com.pedrofactory.monopoly.exception;

import javax.validation.constraints.Email;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String email) {
        super("User with email "+ email + " already exist!!! Registration failed.");
    }
}
