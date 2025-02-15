package com.example.spring_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Operation(summary = "Hello world")
    @GetMapping("")
    public String getUsers() {
        return "Hello World!";
    }
}
