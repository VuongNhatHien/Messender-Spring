package com.example.spring_backend.user;

import com.example.spring_backend.shared.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<Users, Long> {


    public UserService(JpaRepository<Users, Long> repository, ModelMapper mapper) {
        super(repository, mapper);
    }
}
