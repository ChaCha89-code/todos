package com.github.chacha89.todos.todo.dto;

public class TodoCreateErrorResponseDto {
    private int status;
    private String message;

    public TodoCreateErrorResponseDto(int status, String message) {
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
