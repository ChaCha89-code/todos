package com.github.chacha89.todos.todo.dto.response;

import java.util.List;

public class TodoListPaginatedResponseDto<T> {
    private List<TodoGetListResponseDto> todo;       // 페이지 데이터
    private long totalElements; // 전체 데이터 수
    private int totalPages;     // 전체 페이지 수
    private int size;    // 현재 페이지

    public TodoListPaginatedResponseDto(List<TodoGetListResponseDto> data, long totalElements, int totalPages, int size) {

        this.todo = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
    }


    public List<TodoGetListResponseDto> getTodo() {
        return todo;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return size;
    }
}

