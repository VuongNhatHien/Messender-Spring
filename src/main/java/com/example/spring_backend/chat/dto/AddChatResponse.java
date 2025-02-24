package com.example.spring_backend.chat.dto;

import com.example.spring_backend.chat.Chat;
import com.example.spring_backend.user.User;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddChatResponse {
    @JsonUnwrapped
    Chat chat;
    User user1;
    User user2;
}
