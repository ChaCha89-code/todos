package com.github.chacha89.todos.todo.dto.response.dto.dto.response;

import java.util.List;

public class GetTodoListResponseDto {
    private int status;
    private String message;
    private List<GetTodoListResponseDto> data;

    public GetTodoListResponseDto(List<GetTodoListResponseDto> data) {
        this.status = 200;
        this.message = "성공";
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<GetTodoListResponseDto> getData() {
        return data;
    }
}
