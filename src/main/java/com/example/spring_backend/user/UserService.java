package com.example.spring_backend.user;

import com.example.spring_backend.chat.Chat;
import com.example.spring_backend.chat.ChatService;
import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import com.example.spring_backend.user.dto.PreviewChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, Long> {

    private final UserRepository userRepository;
    private final ChatService chatService;

    public UserService(JpaRepository<User, Long> repository, UserRepository userRepository, ChatService chatService) {
        super(repository);
        this.userRepository = userRepository;
        this.chatService = chatService;
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ConflictException(ErrorCode.USER_EXISTED);
        }
        return super.create(user);
    }

    public Chat addChat(Long meId, Long userId) {
        if (!userRepository.existsById(meId) || !userRepository.existsById(userId)) {
            throw new BadRequestException(ErrorCode.USER_NOT_FOUND);
        }
        if (meId.equals(userId)) {
            throw new BadRequestException(ErrorCode.ADD_SELF_CHAT);
        }
        return chatService.create(new Chat(meId, userId));
    }

    public List<User> getNotConnectedUsers(Long meId) {
        return userRepository.getNotConnectedUsers(meId);
    }

    public List<PreviewChatResponse> getPreviews(Long meId) {
        return userRepository.getPreviews(meId);
    }
}
