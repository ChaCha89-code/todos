package com.github.chacha89.todos.todo.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class TodoCreateRequestDto {
    private String title;
    private MultipartFile image;
    private String todoContents;
    private String assignee;
    private String priority;
    private String progress;
    private LocalDate dueDate;

    public TodoCreateRequestDto(String title, MultipartFile image, String todoContents, String assignee, String priority, String progress, LocalDate dueDate) {
        this.title = title;
        this.image = image;
        this.todoContents = todoContents;
        this.assignee = assignee;
        this.priority = priority;
        this.progress = progress;
        this.dueDate = dueDate;
    }

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
