package com.github.chacha89.todos.logout.controller;

import com.github.chacha89.todos.logout.dto.LogoutResponseDto;
import com.github.chacha89.todos.logout.entity.BlacklistToken;
import com.github.chacha89.todos.logout.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    // 속성
    private final LogoutService logoutService;


    // 생성자
    private LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }


    // 기능
    @PostMapping("/logout")
    public LogoutResponseDto logoutAPI(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            LogoutResponseDto responseDto = new LogoutResponseDto(400, "Authorization 헤더가 없습니다.");
            return responseDto;

        } else {

            BlacklistToken blacklistToken = new BlacklistToken(header);

            logoutService.logout(blacklistToken);

            LogoutResponseDto responseDto = new LogoutResponseDto(200, "로그아웃이 완료되었습니다.");
            return responseDto;
        }

    }


}
