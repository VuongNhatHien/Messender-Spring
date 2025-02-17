package com.example.spring_backend.user;

import com.example.spring_backend.shared.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<Users, Long> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
