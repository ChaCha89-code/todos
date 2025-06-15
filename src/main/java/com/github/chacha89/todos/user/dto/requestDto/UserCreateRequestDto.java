package com.github.chacha89.todos.user.dto.requestDto;

import org.springframework.web.multipart.MultipartFile;

public class UserCreateRequestDto {
    // 속성
    private String teamName;
    private String userName;
    private String email;
    private String password;
    private String checkPassword;
    private MultipartFile userImage;

    // 생성자

    public UserCreateRequestDto(String teamName, String userName, String email, String password, String checkPassword, MultipartFile userImage) {
        this.teamName = teamName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
        this.userImage = userImage;
    }

    // 기능
    public String getTeamName() {
        return teamName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public MultipartFile getUserImage() {
        return userImage;
    }
}
