package com.example.wms_system.exceptions;

import com.example.wms_system.models.Order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
    public OrderNotFoundException(Long id){
        super("Order with this id{ " + id + " } not found!");
    }
}
