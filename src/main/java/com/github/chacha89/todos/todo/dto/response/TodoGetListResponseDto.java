package com.github.chacha89.todos.todo.dto.response;
import com.github.chacha89.todos.common.commonEnum.Priority;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoGetListResponseDto {
    private String title;
    private String image;
    private String todoContents;
    private Long userId;
    private Long todoId;
    private String assignee;
    private Priority priority;
    private Progress progress;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TodoGetListResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.image = todo.getImage();
        this.todoContents = todo.getContent();
        this.userId = todo.getUser().getId();
        this.todoId = todo.getId();
        this.assignee = todo.getAssignee();
        this.priority = todo.getPriority();
        this.progress = todo.getProgress();
        this.dueDate = todo.getDueDate();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
    public static TodoGetListResponseDto getTodoListResponseDto(Todo todo){
        return new TodoGetListResponseDto(todo);
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

    public Long getTodoId() {
        return todoId;
    }

    public String getAssignee() {
        return assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public Progress getProgress() {
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
