package com.github.chacha89.todos.todo.dto;

public class TodoDeleteResponseDto {

    private int status;
    private String message;

    public TodoDeleteResponseDto(int status, String message) {
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
