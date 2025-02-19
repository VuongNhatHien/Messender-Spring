package com.example.spring_backend.auth;

import com.example.spring_backend.auth.dto.LoginRequest;
import com.example.spring_backend.auth.dto.LoginResponse;
import com.example.spring_backend.auth.dto.RegisterRequest;
import com.example.spring_backend.shared.ApiResponse;
import com.example.spring_backend.services.JwtService;
import com.example.spring_backend.user.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "Register a new user")
    @PostMapping("register")
    public ApiResponse<User> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User response = authService.register(registerRequest);
        return new ApiResponse<>(response);
    }

    @Operation(summary = "Login to the system")
    @PostMapping("login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest loginUserDto) {
        User authenticatedUser = authService.login(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse response = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return new ApiResponse<>(response);
    }
}
