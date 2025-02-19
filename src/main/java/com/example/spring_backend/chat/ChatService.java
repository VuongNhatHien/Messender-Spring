package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.attachment.AttachmentService;
import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.message.Message;
import com.example.spring_backend.message.MessageService;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ChatService extends BaseService<Chat, Long> {

    private final ChatRepository chatRepository;
    private final MessageService messageService;
    private final AttachmentService attachmentService;

    public ChatService(JpaRepository<Chat, Long> repository, ChatRepository chatRepository, MessageService messageService, AttachmentService attachmentService) {
        super(repository);
        this.chatRepository = chatRepository;
        this.messageService = messageService;
        this.attachmentService = attachmentService;
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
        return messageService.sendMessage(chatId, senderId, input.getMessage());
    }

    public List<Message> sendAttachments(Long chatId, Long senderId, SendAttachmentRequest input) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(senderId) && !chat.getUser2Id().equals(senderId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }

        List<MultipartFile> attachments = input.getAttachments();

        List<Long> attachmentIds = attachments.stream()
                .map(attachmentService::createAttachment)
                .map(Attachment::getId)
                .toList();

        return attachmentIds.stream()
                .map(attachmentId -> messageService.sendAttachment(chatId, senderId, attachmentId))
                .toList();
    }

    public List<Message> getMessages(Long chatId) {
        return messageService.getMessages(chatId);
    }

    public List<Attachment> getAllMedia(Long chatId) {
        return chatRepository.getAllMedia(chatId);
    }

    public List<Attachment> getAllFiles(Long chatId) {
        return chatRepository.getAllFiles(chatId);
    }
}
