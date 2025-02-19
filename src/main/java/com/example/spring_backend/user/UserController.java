package com.example.spring_backend.user;

import com.example.spring_backend.chat.Chat;
import com.example.spring_backend.services.GetMeService;
import com.example.spring_backend.user.dto.PreviewChatResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final GetMeService getMeService;

    @Operation(summary = "Request a new chat")
    @PostMapping("/{userId}/chat-requests")
    public Chat requestChat(@PathVariable("userId") Long userId) {
        Long meId = getMeService.getMeId();
        return userService.addChat(meId, userId);
    }

    @Operation(summary = "Get all not-connected users")
    @GetMapping("not-connected")
    public List<User> getNotConnectedUsers() {
        Long meId = getMeService.getMeId();
        return userService.getNotConnectedUsers(meId);
    }

    @Operation(summary = "Get all preview chats")
    @GetMapping("chats")
    public List<PreviewChatResponse> getPreviews() {
        Long meId = getMeService.getMeId();
        return userService.getPreviews(meId);
    }
}
