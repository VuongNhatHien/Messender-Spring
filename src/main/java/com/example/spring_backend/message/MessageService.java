package com.example.spring_backend.message;

import com.example.spring_backend.shared.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService<Message, Long> {

    private final MessageRepository messageRepository;
    public MessageService(JpaRepository<Message, Long> repository, MessageRepository messageRepository) {
        super(repository);
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(Long chatId, Long senderId, String message) {
        return create(new Message(chatId, senderId, message, null));
    }

    public Message sendAttachment(Long chatId, Long senderId, Long attachmentId) {
        return create(new Message(chatId, senderId, null, attachmentId));
    }
}
