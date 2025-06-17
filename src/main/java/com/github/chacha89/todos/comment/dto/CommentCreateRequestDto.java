package com.github.chacha89.todos.comment.dto;

public class CommentCreateRequestDto {
    private Long userId;
    private Long todoId;
    private String comment;

    public CommentCreateRequestDto(Long userId, Long todoId, String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTodoId() {
        return todoId;
    }

    public String getComment() {
        return comment;
    }
}
