package com.example.wms_system.exceptions;

public class WarehouseNotFoundException extends RuntimeException{
    public WarehouseNotFoundException(String message){
        super(message);
    }
    public WarehouseNotFoundException(Long id){
        super("Warehouse with this id{ " + id + " } not found!");
    }
}
