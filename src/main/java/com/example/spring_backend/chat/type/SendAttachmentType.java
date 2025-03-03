package com.example.spring_backend.chat.type;

import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendAttachmentType {
    Long chatId;
    Long meId;
    SendAttachmentRequest input;
}
