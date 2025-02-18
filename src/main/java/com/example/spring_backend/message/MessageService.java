package com.example.spring_backend.message;

import com.example.spring_backend.shared.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends BaseService<Message, Long> {

    private final MessageRepository messageRepository;
    public MessageService(JpaRepository<Message, Long> repository, MessageRepository messageRepository) {
        super(repository);
        this.messageRepository = messageRepository;
    }

    public List<Message> findByChatId(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
