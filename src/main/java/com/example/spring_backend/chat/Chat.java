package com.example.spring_backend.chat;

import com.example.spring_backend.shared.BaseEntity;
import jakarta.persistence.Column;
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
@Table(name = "chats")
public class Chat extends BaseEntity {
    @Column(name = "user1_id")
    private Long user1Id;
    @Column(name = "user2_id")
    private Long user2Id;
}
