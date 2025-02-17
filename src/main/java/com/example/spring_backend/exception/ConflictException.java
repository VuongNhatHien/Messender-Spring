package com.example.spring_backend.exception;

public class ConflictException extends AppException {
    public ConflictException(String code) {
        super(409, code);
    }
}
