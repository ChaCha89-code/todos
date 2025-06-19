package com.github.chacha89.todos.user.dto.requestDto;

public class UserDeleteRequestDto {
    private String rawPassword;

    public UserDeleteRequestDto() {}  // 기본 생성자

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}