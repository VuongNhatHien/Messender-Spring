package com.example.spring_backend.chat.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class SendAttachmentType {
    Long chatId;
    Long meId;
    MultipartFile attachment;
}
