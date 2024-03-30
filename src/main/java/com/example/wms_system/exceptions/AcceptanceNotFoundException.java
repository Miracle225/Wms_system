package com.example.wms_system.exceptions;

import com.example.wms_system.models.AcceptanceItem;

public class AcceptanceNotFoundException extends RuntimeException{
    public AcceptanceNotFoundException(String message){
        super(message);
    }
    public AcceptanceNotFoundException(Long id){
        super("Good acceptance with this id{ " + id + " } not found!");
    }
}
