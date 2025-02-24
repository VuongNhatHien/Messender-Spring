package com.example.spring_backend.user;

import com.example.spring_backend.chat.dto.AddChatResponse;
import com.example.spring_backend.services.GetMeService;
import com.example.spring_backend.shared.ApiResponse;
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

    @Operation(summary = "Get user information")
    @GetMapping("me")
    public ApiResponse<User> getMe() {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(userService.findUserById(meId));
    }

    @Operation(summary = "Request a new chat")
    @PostMapping("/{userId}/chat-requests")
    public ApiResponse<AddChatResponse> requestChat(@PathVariable("userId") Long userId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(userService.addChat(meId, userId));
    }

    @Operation(summary = "Get all not-connected users")
    @GetMapping("not-connected")
    public ApiResponse<List<User>> getNotConnectedUsers() {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(userService.getNotConnectedUsers(meId));
    }

    @Operation(summary = "Get all preview chats")
    @GetMapping("chats")
    public ApiResponse<List<PreviewChatResponse>> getPreviews() {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(userService.getPreviews(meId));
    }

    @Operation(summary = "Find user by id")
    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable("id") Long id) {
        return new ApiResponse<>(userService.findUserById(id));
    }
}
