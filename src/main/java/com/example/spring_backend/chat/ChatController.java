package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.chat.dto.MessageResponse;
import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.chat.type.SendAttachmentType;
import com.example.spring_backend.metadata.Metadata;
import com.example.spring_backend.rabbit.RabbitSender;
import com.example.spring_backend.services.GetMeService;
import com.example.spring_backend.shared.ApiResponse;
import com.example.spring_backend.user.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("chats")
@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final GetMeService getMeService;
    private final RabbitSender rabbitSender;

    @Operation(summary = "Send a message")
    @PostMapping("/{chatId}/messages")
    public ApiResponse<MessageResponse> sendMessage(@PathVariable("chatId") Long chatId, @RequestBody @Valid SendMessageRequest input) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.sendMessage(chatId, meId, input));
    }

    @Operation(summary = "Get messages")
    @GetMapping("/{chatId}/messages")

    public ApiResponse<List<MessageResponse>> getMessages(@PathVariable("chatId") Long chatId, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1") int page) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getMessages(chatId, meId, limit, page));
    }

    @Operation(summary = "Send attachments")
    @PostMapping("/{chatId}/attachments")
    public ApiResponse<?> sendAttachments(@PathVariable("chatId") Long chatId, @ModelAttribute @Valid SendAttachmentRequest input) throws IOException {
        String filePath = saveToTempStorage(input.getAttachment());
        Long meId = getMeService.getMeId();
        rabbitSender.send(new SendAttachmentType(chatId, meId, filePath));

        return new ApiResponse<>(null);
    }

    @SneakyThrows
    private String saveToTempStorage(MultipartFile file) {
        String projectDir = System.getProperty("user.dir") + "/temp_file/";  // Project root + files folder
        new File(projectDir).mkdirs();  // Creates the directory if it doesn't exist
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();

        Path path = Paths.get(projectDir, fileName);
        Files.write(path, file.getBytes());
        return path.toString();
    }

    @Operation(summary = "Get all media")
    @GetMapping("/{chatId}/attachments/media")
    public ApiResponse<List<Attachment>> getMedia(@PathVariable("chatId") Long chatId, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1") int page) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllMedia(chatId, meId, limit, page));
    }

    @Operation(summary = "Get all files")
    @GetMapping("/{chatId}/attachments/files")
    public ApiResponse<List<Attachment>> getFiles(@PathVariable("chatId") Long chatId, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1") int page) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllFiles(chatId, meId, limit, page));
    }

    @Operation(summary = "Get all links")
    @GetMapping("/{chatId}/links")
    public ApiResponse<List<Metadata>> getLinks(@PathVariable("chatId") Long chatId, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1") int page) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.getAllLinks(chatId, meId, limit, page));
    }

    @Operation(summary = "Get user in chat")
    @GetMapping("/{chatId}/users")
    public ApiResponse<User> findUserInChat(@PathVariable("chatId") Long chatId) {
        Long meId = getMeService.getMeId();
        return new ApiResponse<>(chatService.findUserInChat(chatId, meId));
    }
}
