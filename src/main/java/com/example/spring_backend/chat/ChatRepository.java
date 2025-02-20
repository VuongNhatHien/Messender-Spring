package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.chat.dto.GetMessageResponse;
import com.example.spring_backend.metadata.Metadata;
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
    List<Attachment> getAllMedia(@Param("chatId") Long chatId);

    @Query("""
                SELECT a
                FROM Attachment a
                JOIN Message m ON m.attachmentId = a.id
                WHERE m.chatId = :chatId
                  AND (a.type NOT LIKE 'image%' AND a.type NOT LIKE 'video%')
                ORDER BY a.createdAt DESC
            """)
    List<Attachment> getAllFiles(@Param("chatId") Long chatId);

    @Query("""
                SELECT l
                FROM Metadata l
                JOIN Message m ON m.metadataId = l.id
                WHERE m.chatId = :chatId
                ORDER BY l.createdAt DESC
            """)
    List<Metadata> getAllLinks(@Param("chatId") Long chatId);

    @Query("""
                SELECT new com.example.spring_backend.chat.dto.GetMessageResponse(
                    m,
                    a,
                    l
                )
                FROM Message m LEFT JOIN Attachment a ON m.attachmentId = a.id LEFT JOIN Metadata l ON m.metadataId = l.id
                WHERE m.chatId = :chatId
                ORDER BY m.createdAt DESC
            """)
    List<GetMessageResponse> getMessages(@Param("chatId") Long chatId);
}