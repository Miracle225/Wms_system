package com.example.wms_system.Auth.entity;
import com.example.wms_system.payload.SignUpRequest;

import java.sql.Date;
import java.time.LocalDate;

public abstract class SignUpRequestData {
    public static SignUpRequest getSingleSignUpRequest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("username");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("example@gmail.com");
        return signUpRequest;
    }
}
