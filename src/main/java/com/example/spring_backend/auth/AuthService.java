package com.example.spring_backend.auth;

import com.example.spring_backend.auth.dto.RegisterRequest;
import com.example.spring_backend.user.UserService;
import com.example.spring_backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final ModelMapper mapper;

    public Users register(RegisterRequest registerRequest) {
        Users user = mapper.map(registerRequest, Users.class);

        return userService.create(user);
    }
}
