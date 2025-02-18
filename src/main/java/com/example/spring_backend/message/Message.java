package com.example.spring_backend.message;

import com.example.spring_backend.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "messages")
public class Message extends BaseEntity {
    private Long chatId;
    private Long senderId;
    private String message;
}
