package com.pedrofactory.monopoly.exception;

public class TradeOfferNotFoundException extends Exception {
    public TradeOfferNotFoundException(String id){
        super("User not found with ("+id+") id");
    }
}
