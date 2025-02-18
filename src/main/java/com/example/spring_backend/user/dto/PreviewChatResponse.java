package com.example.spring_backend.user.dto;

import com.example.spring_backend.message.Message;
import com.example.spring_backend.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreviewChatResponse {
    private Long chatId;
    private Message lastMessage;
    private User user;
    private Instant updatedAt;
}

