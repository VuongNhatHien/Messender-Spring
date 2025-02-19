package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    @Query("""
    SELECT a
    FROM Attachment a
    JOIN Message m ON m.attachmentId = a.id
    WHERE m.chatId = :chatId
      AND (a.type LIKE 'image%' OR a.type LIKE 'video%')
    ORDER BY a.createdAt DESC
""")
    List<Attachment> getMedia(@Param("chatId") Long chatId);
}