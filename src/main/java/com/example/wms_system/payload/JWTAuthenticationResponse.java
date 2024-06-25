package com.example.wms_system.payload;

import lombok.*;

@Builder
@NoArgsConstructor
public class JWTAuthenticationResponse {
    private String token;

    public JWTAuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
