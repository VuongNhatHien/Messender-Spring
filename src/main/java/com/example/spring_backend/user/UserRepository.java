package com.example.spring_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    List<User> getNotConnectedUsers(Long meId);
}
