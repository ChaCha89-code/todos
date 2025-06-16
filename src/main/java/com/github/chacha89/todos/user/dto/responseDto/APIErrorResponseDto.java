package com.github.chacha89.todos.user.dto.responseDto;

import org.springframework.http.HttpStatus;

/**
 * 공통 에러 APIResponseDto 입니다
 */
public class APIErrorResponseDto {
    private final int status;
    private final String message;

    private APIErrorResponseDto(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public static APIErrorResponseDto errorResponse(HttpStatus httpStatus, String message) {
        return new APIErrorResponseDto(httpStatus, message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
