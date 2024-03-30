package com.example.wms_system.exceptions;

public class SectorAlreadyExists extends RuntimeException{
    public SectorAlreadyExists(String message){
        super(message);
    }
}
