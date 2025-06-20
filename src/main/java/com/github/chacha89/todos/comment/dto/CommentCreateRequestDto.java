package com.github.chacha89.todos.comment.dto;

public class CommentCreateRequestDto {
    private Long todoId;
    private String comment;

    public CommentCreateRequestDto(Long todoId, String comment) {
        this.todoId = todoId;
        this.comment = comment;
    }

    public Long getTodoId() {
        return todoId;
    }

    public String getComment() {
        return comment;
    }
}
