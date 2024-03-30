package com.example.wms_system.exceptions;

public class UserDuplicateEmail extends RuntimeException{
    public UserDuplicateEmail(String email){
        super("User with this email{ " + email + " }already exists");
    }
}
