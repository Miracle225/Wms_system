package com.example.wms_system.Auth;
/*
import com.example.wms_system.models.User;
import com.example.wms_system.payload.JWTAuthenticationResponse;
import com.example.wms_system.payload.SignInRequest;
import com.example.wms_system.payload.SignUpRequest;
import com.example.wms_system.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationService authenticationService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testSignUp() throws Exception {
        User user = new User();
        when(authenticationService.signUp(any(SignUpRequest.class))).thenReturn(user);

        mockMvc.perform(post("http://localhost:8008/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@gmail.com\",\"username\":\"ivan007\",\"password\":\"qwerty123\"}"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testSignIn() throws Exception {
        JWTAuthenticationResponse response = new JWTAuthenticationResponse("token");
        when(authenticationService.signIn(any(SignInRequest.class))).thenReturn(response);

        mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"ivan007\",\"password\":\"qwerty123\"}"))
                .andExpect(status().isOk());
    }
}
*/