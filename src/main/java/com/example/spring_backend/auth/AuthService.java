package com.example.spring_backend.auth;

import com.example.spring_backend.auth.dto.LoginRequest;
import com.example.spring_backend.auth.dto.RegisterRequest;
import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.shared.ErrorCode;
import com.example.spring_backend.user.User;
import com.example.spring_backend.user.UserRepository;
import com.example.spring_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;

    public User register(RegisterRequest registerRequest) {
        User user = mapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles("ROLE_USER");
        return userService.create(user);
    }

    public User login(LoginRequest input) {
        if (!userService.existsByUsername(input.getUsername())) {
            throw new BadRequestException(ErrorCode.USER_NOT_FOUND);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUsername(),
                            input.getPassword()
                    )
            );

            return userService.findByUsername(input.getUsername());
        } catch (Exception e) {
            throw new BadRequestException(ErrorCode.WRONG_PASSWORD);
        }
    }
}
