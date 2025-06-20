package com.github.chacha89.todos.auth.Login.dto.responseDto;

import org.springframework.http.HttpStatus;

public class LoginResponseDto {
    private int status;
    private String message;
    private String bearerJWTToken;

    public LoginResponseDto(HttpStatus status, String JWTToken) {
        this.status = status.value();
        this.message = "로그인이 완료되었습니다.";
        this.bearerJWTToken = JWTToken;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getJWTToken() {
        return bearerJWTToken;
    }
}
