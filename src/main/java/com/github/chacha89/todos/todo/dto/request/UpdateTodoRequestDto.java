package com.github.chacha89.todos.todo.dto.request;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateTodoRequestDto {
    private String title;
    private String contents;
    private String assignee;
    private String progress;
    private String priority;
    private String image;
}

