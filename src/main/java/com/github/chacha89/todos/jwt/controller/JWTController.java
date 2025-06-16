package com.github.chacha89.todos.jwt.controller;

import com.github.chacha89.todos.jwt.service.JWTService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/refresh")
public class JWTController {
    private final JWTService jwtService;

    public JWTController(JWTService jwtService) {
        this.jwtService = jwtService;
    }
    public void refreshToken() {}
}
