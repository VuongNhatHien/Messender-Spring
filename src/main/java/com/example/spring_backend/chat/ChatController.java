package com.example.spring_backend.chat;

import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.message.Message;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("chats")
@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @Operation(summary = "Send a message")
    @PostMapping("/{chatId}/messages")
    public Message sendMessage(@PathVariable("chatId") Long chatId, @RequestBody SendMessageRequest input) {
        Long meId = 1L;
        return chatService.sendMessage(chatId, meId, input);
    }
}
