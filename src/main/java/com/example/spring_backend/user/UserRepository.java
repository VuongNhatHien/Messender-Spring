package com.example.spring_backend.user;

import com.example.spring_backend.user.dto.PreviewChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

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
                AND (:searchName = "" OR
                 LOWER(u.username) LIKE LOWER(CONCAT('%', :searchName, '%')) OR
                 LOWER(u.displayName) LIKE LOWER(CONCAT('%', :searchName, '%')))
                ORDER BY u.id
                LIMIT :limit OFFSET :offset
            """)
    List<User> getNotConnectedUsers(
            @Param("meId") Long meId,
            @Param("searchName") String searchName,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

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
                WHERE (c.user1Id = :meId OR c.user2Id = :meId)
                AND (:searchName = "" OR
                     LOWER(u.username) LIKE LOWER(CONCAT('%', :searchName, '%')) OR
                     LOWER(u.displayName) LIKE LOWER(CONCAT('%', :searchName, '%')))
                ORDER BY c.updatedAt DESC
                LIMIT :limit OFFSET :offset
            """)
    List<PreviewChatResponse> getPreviews(
            @Param("meId") Long meId,
            @Param("searchName") String searchName,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
