package com.example.wms_system.exceptions;

import com.example.wms_system.models.Operation;

public class OperationNotFoundException extends RuntimeException{
    public OperationNotFoundException(String message){
        super(message);
    }
    public OperationNotFoundException(Long id){
        super("Operation with this id{ " + id + " } not found!");
    }
}
