package com.example.wms_system.exceptions;

public class CustomerAlreadyExists extends RuntimeException{
    public CustomerAlreadyExists(String message){
        super(message);
    }
}
