package com.github.chacha89.todos.comment.dto;

import com.github.chacha89.todos.comment.entity.Comment;


import java.time.LocalDateTime;

public class CommentListResponseDto {
    //속
    private Long id;
    private Long userId;
    private Long todoId;
    private String comment;
    private LocalDateTime createAt;
    private LocalDateTime updateAT;

    //생
    public CommentListResponseDto(Comment comment ) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.todoId = comment.getTodo().getId();
        this.comment = comment.getComment();
        this.createAt = comment.getCreatedAt();
        this.updateAT = comment.getUpdatedAt();
    }

    //기

    public Long getId() {
        return id;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
