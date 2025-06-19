package com.github.chacha89.todos.todo.dto.request;

import lombok.Getter;

@Getter
public class TodoDetailRequestDto {
    private Long todoId;

    public TodoDetailRequestDto(Long todoId){this.todoId =todoId;}
}
