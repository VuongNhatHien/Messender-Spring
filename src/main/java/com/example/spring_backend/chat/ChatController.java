package com.example.spring_backend.chat;

import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.message.Message;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Get messages")
    @GetMapping("/{chatId}/messages")
    public List<Message> getMessages(@PathVariable("chatId") Long chatId) {
        return chatService.getMessages(chatId);
    }
}
