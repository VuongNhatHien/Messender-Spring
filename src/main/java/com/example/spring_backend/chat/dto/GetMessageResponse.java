package com.example.spring_backend.chat.dto;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.message.Message;
import com.example.spring_backend.metadata.Metadata;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMessageResponse {
    @JsonUnwrapped
    private Message message;
    private Attachment attachment;
    private Metadata metadata;
}
