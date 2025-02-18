package com.example.spring_backend.message;

import com.example.spring_backend.shared.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService<Message, Long> {

    public MessageService(JpaRepository<Message, Long> repository) {
        super(repository);
    }
}
