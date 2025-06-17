package com.github.chacha89.todos.logout.controller;

import com.github.chacha89.todos.jwt.service.JWTService;
import com.github.chacha89.todos.logout.dto.LogoutResponseDto;
import com.github.chacha89.todos.logout.entity.BlacklistToken;
import com.github.chacha89.todos.logout.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class LogoutController {

    // 속성
    private final LogoutService logoutService;
    private final JWTService jwtService;


    // 생성자
    public LogoutController(LogoutService logoutService, JWTService jwtService) {
        this.logoutService = logoutService;
        this.jwtService = jwtService;
    }


    // 기능
    @PostMapping("/logout")
    public LogoutResponseDto logoutAPI(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            LogoutResponseDto responseDto = new LogoutResponseDto(400, "Authorization 헤더가 없습니다.");

            return responseDto;

        } else {
            String token = header.substring(7);
            logoutService.logout(token);
            LogoutResponseDto responseDto = new LogoutResponseDto(200, "로그아웃이 완료되었습니다.");
            return responseDto;
        }

    }


}
