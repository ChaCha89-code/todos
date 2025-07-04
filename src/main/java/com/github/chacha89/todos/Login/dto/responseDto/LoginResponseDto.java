package com.github.chacha89.todos.Login.dto.responseDto;

import org.springframework.http.HttpStatus;

//public class LoginResponseDto {


    //    private int status;
//    private String message;
//    private String bearerJWTToken;
//
//    public LoginResponseDto(HttpStatus status, String JWTToken) {
//        this.status = status.value();
//        this.message = "로그인이 완료되었습니다.";
//        this.bearerJWTToken = JWTToken;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getJWTToken() {
//        return bearerJWTToken;
//    }
//}
public class LoginResponseDto {
    private int status;
    private String message;
    private String bearerJwtToken;

    public LoginResponseDto(int status, String message, String bearerJwtToken) {
        this.status = status;
        this.message = "토큰이 생성 되었습니다.";
        this.bearerJwtToken = bearerJwtToken;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage(){
        return message;
    }
    public String getBearerJwtToken(){
        return bearerJwtToken;
    }
}