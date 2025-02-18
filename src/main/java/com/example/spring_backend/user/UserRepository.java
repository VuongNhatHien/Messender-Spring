package com.example.spring_backend.user;

import com.example.spring_backend.user.dto.PreviewChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    @Query("""
                SELECT u
                FROM User u
                WHERE u.id != :meId
                AND u.id NOT IN (
                    SELECT CASE
                        WHEN c.user1Id = :meId THEN c.user2Id
                        ELSE c.user1Id
                    END
                    FROM Chat c
                    WHERE c.user1Id = :meId OR c.user2Id = :meId
                )
            """)
    List<User> getNotConnectedUsers(@Param("meId") Long meId);

    @Query("""
                SELECT new com.example.spring_backend.user.dto.PreviewChatResponse(
                    c.id,
                    m,
                    u,
                    c.updatedAt
                )
                FROM Chat c
                LEFT JOIN Message m ON m.chatId = c.id
                    AND m.createdAt = (
                        SELECT MAX(m2.createdAt)
                        FROM Message m2
                        WHERE m2.chatId = c.id
                    )
                JOIN User u ON u.id = CASE
                    WHEN c.user1Id = :meId THEN c.user2Id
                    ELSE c.user1Id
                END
                WHERE c.user1Id = :meId OR c.user2Id = :meId
                ORDER BY c.updatedAt DESC
            """)
    List<PreviewChatResponse> getPreviews(@Param("meId") Long meId);
}
