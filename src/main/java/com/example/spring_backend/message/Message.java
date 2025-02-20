package com.example.spring_backend.message;

import com.example.spring_backend.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "messages")
public class Message extends BaseEntity {
    private Long chatId;
    private Long senderId;
    private String message;
    private Long attachmentId;
    private Long metadataId;
}
