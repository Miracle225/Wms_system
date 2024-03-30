package com.example.wms_system.exceptions;

public class GoodAlreadyExists extends RuntimeException{
    public GoodAlreadyExists(String message){
        super(message);
    }
    public GoodAlreadyExists(Long code){
        super("Good with this code: " + code + " already exists!");
    }
}
