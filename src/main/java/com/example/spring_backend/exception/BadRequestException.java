package com.example.spring_backend.exception;

public class BadRequestException extends AppException {
    public BadRequestException(String code) {
        super(400, code);
    }
}
