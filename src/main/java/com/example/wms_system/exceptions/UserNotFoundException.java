package com.example.wms_system.exceptions;

import com.example.wms_system.models.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(Long id){
        super("User with this id{ " + id + " } not found!");
    }
}
