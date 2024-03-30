package com.example.wms_system.exceptions;

public class GoodNotFoundException extends RuntimeException{
    public GoodNotFoundException(String message){
        super(message);
    }
    public GoodNotFoundException(Long id){
        super("Good with this id{ " + id + " } not found!");
    }
}
