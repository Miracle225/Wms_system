package com.example.wms_system.exceptions;

public class WarehouseAlreadyExists extends RuntimeException{
    public WarehouseAlreadyExists(String message){
        super(message);
    }
}
