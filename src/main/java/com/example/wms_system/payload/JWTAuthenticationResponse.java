package com.example.wms_system.payload;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationResponse {
    private String token;
}
