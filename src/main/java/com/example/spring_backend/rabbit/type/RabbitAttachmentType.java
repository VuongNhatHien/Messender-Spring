package com.example.spring_backend.rabbit.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RabbitAttachmentType {
    Long attachmentId;
    String filePath;
}
