package com.example.wms_system.services;

import com.example.wms_system.dto.UserDto;
import com.example.wms_system.enums.Role;
import com.example.wms_system.models.User;
import com.example.wms_system.payload.JWTAuthenticationResponse;
import com.example.wms_system.payload.SignInRequest;
import com.example.wms_system.payload.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public User signUp(SignUpRequest request) {

        var userDto = new UserDto(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getUsername());
        userService.create(userDto);
        // var jwt = jwtService.generateToken(user);
        return  userDto.toUserEntity();
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JWTAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JWTAuthenticationResponse(jwt);
    }
}
