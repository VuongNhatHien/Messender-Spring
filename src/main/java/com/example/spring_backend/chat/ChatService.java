package com.example.spring_backend.chat;

import com.example.spring_backend.attachment.Attachment;
import com.example.spring_backend.attachment.AttachmentService;
import com.example.spring_backend.chat.dto.MessageResponse;
import com.example.spring_backend.chat.dto.SendAttachmentRequest;
import com.example.spring_backend.chat.dto.SendMessageRequest;
import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.message.Message;
import com.example.spring_backend.message.MessageRepository;
import com.example.spring_backend.message.MessageService;
import com.example.spring_backend.metadata.Metadata;
import com.example.spring_backend.metadata.MetadataService;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import com.example.spring_backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatService extends BaseService<Chat, Long> {

    private final ChatRepository chatRepository;
    private final MessageService messageService;
    private final AttachmentService attachmentService;
    private final MetadataService metadataService;
    private final MessageRepository messageRepository;

    public ChatService(JpaRepository<Chat, Long> repository, ChatRepository chatRepository, MessageService messageService, AttachmentService attachmentService, MetadataService metadataService, MessageRepository messageRepository) {
        super(repository);
        this.chatRepository = chatRepository;
        this.messageService = messageService;
        this.attachmentService = attachmentService;
        this.metadataService = metadataService;
        this.messageRepository = messageRepository;
    }

    public Chat create(Chat chat) {
        if (chatRepository.existsByUser1IdAndUser2Id(chat.getUser1Id(), chat.getUser2Id())) {
            throw new ConflictException(ErrorCode.CHAT_EXISTED);
        }
        return super.create(chat);
    }

    private String extractFirstUrl(String text) {
        String urlRegex = "(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?\\/[a-zA-Z0-9]{2,}|((https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?)|(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)[a-zA-Z0-9]{2,}\\.[a-zA-Z0-9]{2,}\\.[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})?";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public MessageResponse sendMessage(Long chatId, Long senderId, SendMessageRequest input) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));
        if (!chat.getUser1Id().equals(senderId) && !chat.getUser2Id().equals(senderId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        String message = input.getMessage();
        String url = extractFirstUrl(message);

        Metadata metadata = null;
        if (url != null) {
            metadata = metadataService.extractMetadata(url);
        }

        Long metadataId = null;
        if (metadata != null) {
            metadataId = metadata.getId();
        }
        var res =  messageService.sendMessage(chatId, senderId, message, metadataId);

        return new MessageResponse(res, null, metadata);
    }

    public MessageResponse sendAttachments(Long chatId, Long senderId, SendAttachmentRequest input) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(senderId) && !chat.getUser2Id().equals(senderId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }

        MultipartFile attachment = input.getAttachment();

        Attachment attachmentRes = attachmentService.createAttachment(attachment);
        Message message = messageService.create(new Message(chatId, senderId, null, attachmentRes.getId(), null));

        return new MessageResponse(message, attachmentRes, null);
    }

    public List<MessageResponse> getMessages(Long chatId, Long meId, int limit, int page) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(meId) && !chat.getUser2Id().equals(meId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        int offset = (page - 1) * limit;
        return chatRepository.getMessages(chatId, limit, offset);
    }

    public List<Attachment> getAllMedia(Long chatId, Long meId, int limit, int page) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(meId) && !chat.getUser2Id().equals(meId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        int offset = (page - 1) * limit;
        return chatRepository.getAllMedia(chatId, limit, offset);
    }

    public List<Attachment> getAllFiles(Long chatId, Long meId, int limit, int page) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(meId) && !chat.getUser2Id().equals(meId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        int offset = (page - 1) * limit;
        return chatRepository.getAllFiles(chatId, limit, offset);
    }

    public List<Metadata> getAllLinks(Long chatId, Long meId, int limit, int page) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(meId) && !chat.getUser2Id().equals(meId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }
        int offset = (page - 1) * limit;
        return chatRepository.getAllLinks(chatId, limit, offset);
    }

    public User findUserInChat(Long chatId, Long meId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.CHAT_NOT_FOUND));

        if (!chat.getUser1Id().equals(meId) && !chat.getUser2Id().equals(meId)) {
            throw new BadRequestException(ErrorCode.NOT_IN_CHAT);
        }

        return chatRepository.findUserInChat(chatId, meId);
    }
}
