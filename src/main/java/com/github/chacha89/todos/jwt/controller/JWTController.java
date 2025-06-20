package com.github.chacha89.todos.jwt.controller;

import com.github.chacha89.todos.jwt.service.JWTUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/refresh")
public class JWTController {
    private final JWTUtil jwtUtil;

    public JWTController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    public void refreshToken() {}
}
