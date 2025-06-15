package com.github.chacha89.todos.team.dto.responseDto;

public class TeamCreateResponseDto {
    // 속성
    private int status;
    private String message;

    // 생성자

    public TeamCreateResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
