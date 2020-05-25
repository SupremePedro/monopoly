package com.pedrofactory.monopoly.exception;

public class UserNotFoundException  extends  Exception{
    public UserNotFoundException(String id){
        super("User not found with ("+id+") id");
    }
}
