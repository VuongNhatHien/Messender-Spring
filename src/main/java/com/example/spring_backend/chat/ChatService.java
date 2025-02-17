package com.example.spring_backend.chat;

import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService extends BaseService<Chat, Long> {

    private final ChatRepository chatRepository;

    public ChatService(JpaRepository<Chat, Long> repository, ChatRepository chatRepository) {
        super(repository);
        this.chatRepository = chatRepository;
    }

    public Chat create(Chat chat) {
        if (chatRepository.existsByUser1IdAndUser2Id(chat.getUser1Id(), chat.getUser2Id())) {
            throw new ConflictException(ErrorCode.CHAT_EXISTED);
        }
        return super.create(chat);
    }
}
