package com.example.spring_backend.chat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendAttachmentRequest {
    @NotNull(message = "At least one attachment is required")
    private MultipartFile attachment;
}
