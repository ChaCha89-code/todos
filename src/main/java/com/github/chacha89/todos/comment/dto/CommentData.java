package com.github.chacha89.todos.comment.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public class CommentData {
    private Long id;
    private Long userId;
    private Long todoId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentData(Long id, Long userId, Long todoId, String comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.todoId = todoId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
