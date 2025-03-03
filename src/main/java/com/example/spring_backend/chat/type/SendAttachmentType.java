package com.example.spring_backend.chat.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SendAttachmentType {
    Long chatId;
    Long meId;
    String filePath;
}
