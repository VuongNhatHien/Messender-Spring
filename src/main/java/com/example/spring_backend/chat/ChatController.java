package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.message.Message;
import com.example.spring_backend.services.GetMeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("chats")
@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final GetMeService getMeService;

    @Operation(summary = "Send a message")
    @PostMapping("/{chatId}/messages")
    public Message sendMessage(@PathVariable("chatId") Long chatId, @RequestBody @Valid SendMessageRequest input) {
        Long meId = getMeService.getMeId();
        return chatService.sendMessage(chatId, meId, input);
    }

    @Operation(summary = "Get messages")
    @GetMapping("/{chatId}/messages")

    public List<Message> getMessages(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return chatService.getMessages(chatId, meId);
    }

    @Operation(summary = "Send attachments")
    @PostMapping("/{chatId}/attachments")
    public List<Message> sendAttachments(
            @PathVariable("chatId") Long chatId,
            @ModelAttribute @Valid SendAttachmentRequest input
    ) {
        Long meId = getMeService.getMeId();
        return chatService.sendAttachments(chatId, meId, input);
    }

    @Operation(summary = "Get all media")
    @GetMapping("/{chatId}/attachments/media")
    public List<Attachment> getMedia(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return chatService.getAllMedia(chatId, meId);
    }

    @Operation(summary = "Get all files")
    @GetMapping("/{chatId}/attachments/files")
    public List<Attachment> getFiles(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return chatService.getAllFiles(chatId, meId);
    }
}
