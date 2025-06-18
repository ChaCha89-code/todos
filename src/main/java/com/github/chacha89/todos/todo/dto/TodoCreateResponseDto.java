package com.github.chacha89.todos.todo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoCreateResponseDto {
    // 속성
    private String title;
    private String image;
    private String todoContents;
    private Long userId;
    private String assignee;
    private String priority;
    private String progress;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public TodoCreateResponseDto(String title, String image, String todoContents, Long userId, String assignee, String priority, String progress, LocalDate dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.image = image;
        this.todoContents = todoContents;
        this.userId = userId;
        this.assignee = assignee;
        this.priority = priority;
        this.progress = progress;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // 기능
    public String getTitle() { return title; }

    public String getImage() { return image; }

    public String getTodoContents() { return todoContents; }

    public Long getUserId() { return userId; }

    public String getAssignee() {
        return assignee;
    }

    public String getPriority() {
        return priority;
    }

    public String getProgress() {
        return progress;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
