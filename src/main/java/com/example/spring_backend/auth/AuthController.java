package com.example.spring_backend.auth;

import com.example.spring_backend.auth.dto.RegisterRequest;
import com.example.spring_backend.shared.ApiResponse;
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

    @Operation(summary = "Register a new user")
    @PostMapping("register")
    public ApiResponse<User> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User response = authService.register(registerRequest);
        return new ApiResponse<>(response);
    }
}
