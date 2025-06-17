package com.github.chacha89.todos.comment.dto;

public class CommentCreateErrorResponseDto {
    private int status;
    private String message;

    public CommentCreateErrorResponseDto(int status, String message) {
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
