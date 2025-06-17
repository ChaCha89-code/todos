package com.github.chacha89.todos.logout.dto;

public class LogoutResponseDto {

    private int status;
    private String message;

    public LogoutResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
