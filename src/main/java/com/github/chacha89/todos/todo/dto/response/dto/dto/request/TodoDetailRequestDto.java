package com.github.chacha89.todos.todo.dto.response.dto.dto.request;

import lombok.Getter;

@Getter
public class TodoDetailRequestDto {
    private Long todoId;

    public TodoDetailRequestDto(Long todoId){this.todoId =todoId;}
}
