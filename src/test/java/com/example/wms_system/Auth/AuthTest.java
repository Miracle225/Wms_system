package com.example.wms_system.Auth;

import com.example.wms_system.dto.UserDto;
import com.example.wms_system.models.User;
import com.example.wms_system.payload.JWTAuthenticationResponse;
import com.example.wms_system.payload.SignInRequest;
import com.example.wms_system.payload.SignUpRequest;
import com.example.wms_system.services.AuthenticationService;
import com.example.wms_system.services.UserService;
import com.example.wms_system.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUp() {
        SignUpRequest request = new SignUpRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");
        request.setUsername("username");

        UserDto userDto = new UserDto(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getUsername());
        User user = userDto.toUserEntity();

        when(userService.create(any(UserDto.class))).thenReturn(user);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        User result = authenticationService.signUp(request);

        assertEquals(result.getEmail(),user.getEmail());
        assertEquals(result.getUsername(),user.getUsername());
        //assert(result.getPassword().equals(user.getPassword()));
    }


    @Test
    public void testSignIn() {
        SignInRequest request = new SignInRequest();
        request.setUsername("username");
        request.setPassword("password");

        JWTAuthenticationResponse response = new JWTAuthenticationResponse("token");

        when(userService.userDetailsService()).thenReturn(userDetailsService);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userService.userDetailsService().loadUserByUsername(any(String.class))).thenReturn(new User());
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        JWTAuthenticationResponse result = authenticationService.signIn(request);

        //assertThrows(NullPointerException.class,()->userService.userDetailsService());
        assertEquals(result.getToken(),response.getToken());
    }
}