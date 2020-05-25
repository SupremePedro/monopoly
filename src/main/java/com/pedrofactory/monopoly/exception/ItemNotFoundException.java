package com.pedrofactory.monopoly.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String id){
        super("Item not found with ("+id+") id");
    }
}
