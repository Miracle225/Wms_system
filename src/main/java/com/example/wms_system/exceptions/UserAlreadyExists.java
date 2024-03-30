package com.example.wms_system.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(){
        super("User already exists. Please try again");
    }
}
