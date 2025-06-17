package com.github.chacha89.todos.comment.dto;

public class CommentCreateRequestDto {
    private String comment;

    public CommentCreateRequestDto(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
