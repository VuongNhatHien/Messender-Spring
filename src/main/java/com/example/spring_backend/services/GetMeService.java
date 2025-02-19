package com.example.spring_backend.services;

import com.example.spring_backend.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetMeService {
    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Long getMeId() {
        return getMe().getId();
    }
}
