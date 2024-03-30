package com.example.wms_system.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
    public CustomerNotFoundException(Long id){
        super("Customer with this id{ " + id + " } not found!");
    }
}
