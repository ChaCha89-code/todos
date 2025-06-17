package com.github.chacha89.todos.todo.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class TodoCreateRequestDto {
    private Long userId;
    private String title;
    private MultipartFile image;
    private String todoContents;
    private String assignee;
    private String priority;
    private String progress;
    private LocalDate dueDate;

    public TodoCreateRequestDto(Long userId, String title, MultipartFile image, String todoContents, String assignee, String priority, String progress, LocalDate dueDate) {
        this.userId = userId;
        this.title = title;
        this.image = image;
        this.todoContents = todoContents;
        this.assignee = assignee;
        this.priority = priority;
        this.progress = progress;
        this.dueDate = dueDate;
    }

    public Long getUserId() {return userId;}

    public String getTitle() {
        return title;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getTodoContents() {
        return todoContents;
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

}
