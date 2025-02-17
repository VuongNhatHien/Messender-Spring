package com.example.spring_backend.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}