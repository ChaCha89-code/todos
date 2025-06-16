package com.github.chacha89.todos.user.dto.requestDto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private String newPassword;
    private String newUserImage;
    private String confirmPassword;

}
