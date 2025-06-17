package com.github.chacha89.todos.todo.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class TodoCreateResponseDto {
    private String title;
    private String image;
    private String todoContents;
    private Long userId;
    private String assignee;
    private String priority;
    private String progress;
    private LocalDate dueDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public TodoCreateResponseDto(String title, String image, String todoContents, Long userId, String assignee, String priority, String progress, LocalDate dueDate, LocalDate createdAt, LocalDate updatedAt) {
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

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getTodoContents() {
        return todoContents;
    }

    public Long getUserId() {
        return userId;
    }

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
