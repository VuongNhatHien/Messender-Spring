package com.example.spring_backend.user;

import com.example.spring_backend.exception.ConflictException;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {

    private final UserRepository userRepository;

    public UserService(JpaRepository<User, Long> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ConflictException(ErrorCode.USER_EXISTED);
        }
        return super.create(user);
    }
}
