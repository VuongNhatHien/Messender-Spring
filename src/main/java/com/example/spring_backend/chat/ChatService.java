package com.example.spring_backend.chat;

import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.message.Message;
import com.example.spring_backend.message.MessageService;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService extends BaseService<Chat, Long> {

    private final ChatRepository chatRepository;
    private final MessageService messageService;

    public ChatService(JpaRepository<Chat, Long> repository, ChatRepository chatRepository, MessageService messageService) {
        super(repository);
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    public Chat create(Chat chat) {
        if (chatRepository.existsByUser1IdAndUser2Id(chat.getUser1Id(), chat.getUser2Id())) {
            throw new ConflictException(ErrorCode.CHAT_EXISTED);
        }
        return super.create(chat);
    }

    public Message sendMessage(Long chatId, Long senderId, SendMessageRequest input) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));
        if (!chat.getUser1Id().equals(senderId) && !chat.getUser2Id().equals(senderId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        return messageService.create(new Message(chatId, senderId, input.getMessage()));
    }
}
