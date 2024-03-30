package com.example.wms_system.exceptions;

public class ProviderNotFoundException extends RuntimeException{
    public ProviderNotFoundException(String message){
        super(message);
    }
    public ProviderNotFoundException(Long id){
        super("Provider with this id{ " + id + " } not found!");
    }
}
