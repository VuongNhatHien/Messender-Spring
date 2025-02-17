package com.example.spring_backend.user;

import com.example.spring_backend.chat.Chat;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Request a new chat")
    @PostMapping("/{userId}/chat-requests")
    public Chat requestChat(@PathVariable("userId") Long userId) {
        Long meId = 1L;
        return userService.addChat(meId, userId);
    }
}
