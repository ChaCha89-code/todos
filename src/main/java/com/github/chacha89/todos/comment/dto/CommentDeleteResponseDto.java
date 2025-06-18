package com.github.chacha89.todos.comment.dto;

public class CommentDeleteResponseDto {

    private int status;
    private String message;

    public CommentDeleteResponseDto(int status, String message) {
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
