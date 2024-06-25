package com.example.wms_system.controllers;

        import com.example.wms_system.models.User;
        import com.example.wms_system.payload.JWTAuthenticationResponse;
        import com.example.wms_system.payload.SignInRequest;
        import com.example.wms_system.payload.SignUpRequest;
        import com.example.wms_system.services.AuthenticationService;
        import jakarta.validation.Valid;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.Collections;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
/*
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Назва HTML сторінки (register.html) у каталозі шаблонів
    }
    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            User user = authenticationService.signUp(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
        }
    }
*/
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@ModelAttribute @Valid SignInRequest request) {
        try {
            JWTAuthenticationResponse jwtResponse = authenticationService.signIn(request);
            return ResponseEntity.ok(jwtResponse);
        }catch (Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
    }
    }

}
