package com.example.wms_system.exceptions;

import com.example.wms_system.models.WarehouseSector;

public class WarehouseSectorNotFoundException extends RuntimeException{
    public WarehouseSectorNotFoundException(String message){
        super(message);
    }
    public WarehouseSectorNotFoundException(Long id){
        super("Warehouse sector with this id{ " + id + " } not found!");
    }
}
