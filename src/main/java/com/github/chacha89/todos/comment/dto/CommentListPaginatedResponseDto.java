package com.github.chacha89.todos.comment.dto;

import java.util.List;

public class CommentListPaginatedResponseDto<T> {

    private List<CommentListResponseDto> comment;       // 페이지 데이터
    private long totalElements; // 전체 데이터 수
    private int totalPages;     // 전체 페이지 수
    private int currentPage;    // 현재 페이지

    public CommentListPaginatedResponseDto(List<CommentListResponseDto> data, long totalElements, int totalPages, int currentPage) {

        this.comment = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }



    public List<CommentListResponseDto> getData() {
        return comment;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
