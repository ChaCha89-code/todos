package com.github.chacha89.todos.user.dto.responseDto;


import lombok.Getter;
import com.github.chacha89.todos.user.entity.User;

@Getter
public class UserInfoResponseDto {
    private long id;
    private String email;
//    private String paasword;
    private String userName;
    private String userImage;
    private int status;
    private String message;


    public static UserInfoResponseDto from(User user, String message) {
        UserInfoResponseDto dto = new UserInfoResponseDto();
        dto.id = user.getId();
        dto.email = user.getEmail();
//        dto.password = user.getPassword();
        dto.userName = user.getUserName();
        dto.userImage = user.getUserImage();
        dto.status = 200; // 또는 enum/status code 기반으로 설정 가능
        dto.message = message;
        return dto;
    }

}




