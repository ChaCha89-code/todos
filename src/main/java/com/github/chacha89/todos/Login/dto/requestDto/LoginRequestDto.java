package com.github.chacha89.todos.Login.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//public class LoginRequestDto {
//    @Email
//    @NotBlank
//    private final String email;
//    @NotBlank
//    private final String password;
//
//    public LoginRequestDto(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//}

public class LoginRequestDto{


    @Email //이메일 양식을 사용하게 하는
    @NotBlank //데이터를 필수로 넣어야하는
    private final String email;
    @NotBlank
    private final String password;
    // 생성자
    public LoginRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
    // 기능 다른곳에서 사용할 수 있게 게터 사용
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}