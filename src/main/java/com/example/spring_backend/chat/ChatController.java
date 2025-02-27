package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.chat.dto.MessageResponse;
import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.metadata.Metadata;
import com.example.spring_backend.services.GetMeService;
import com.example.spring_backend.shared.ApiResponse;
import com.example.spring_backend.user.User;
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
    public ApiResponse<MessageResponse> sendMessage(@PathVariable("chatId") Long chatId, @RequestBody @Valid SendMessageRequest input) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.sendMessage(chatId, meId, input));
    }

    @Operation(summary = "Get messages")
    @GetMapping("/{chatId}/messages")

    public ApiResponse<List<MessageResponse>> getMessages(@PathVariable("chatId") Long chatId,
                                                          @RequestParam(defaultValue = "10") int limit,
                                                          @RequestParam(defaultValue = "1") int page) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getMessages(chatId, meId, limit, page));
    }

    @Operation(summary = "Send attachments")
    @PostMapping("/{chatId}/attachments")
    public ApiResponse<MessageResponse> sendAttachments(
            @PathVariable("chatId") Long chatId,
            @ModelAttribute @Valid SendAttachmentRequest input
    ) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.sendAttachments(chatId, meId, input));
    }

    @Operation(summary = "Get all media")
    @GetMapping("/{chatId}/attachments/media")
    public ApiResponse<List<Attachment>> getMedia(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllMedia(chatId, meId));
    }

    @Operation(summary = "Get all files")
    @GetMapping("/{chatId}/attachments/files")
    public ApiResponse<List<Attachment>> getFiles(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllFiles(chatId, meId));
    }

    @Operation(summary = "Get all links")
    @GetMapping("/{chatId}/links")
    public ApiResponse<List<Metadata>> getLinks(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllLinks(chatId, meId));
    }

    @Operation(summary = "Get user in chat")
    @GetMapping("/{chatId}/users")
    public ApiResponse<User> findUserInChat(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.findUserInChat(chatId, meId));
    }
}
