package com.example.wms_system.exceptions;

public class AcceptanceItemNotFoundException extends RuntimeException{
    public AcceptanceItemNotFoundException(String message){
        super(message);
    }
    public AcceptanceItemNotFoundException(Long id){
        super("Acceptance item with this id{ " + id + " } not found!");
    }
    public AcceptanceItemNotFoundException(String message , Long accId,Long goodId){
        super(message);
    }
}
