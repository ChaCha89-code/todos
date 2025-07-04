package com.github.chacha89.todos.user.dto.responseDto;

import lombok.Getter;

@Getter
public class UserCreateResponseDto {

    //    // 속성
//    private int status;
//    private String message;
//
//    // 생성자
//    public UserCreateResponseDto(int status, String message) {
//        this.status = status;
//        this.message = message;
//    }
//
//    // 기능
//    public int getStatus() {
//        return status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
    private int status;
    private String message;

    public UserCreateResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

