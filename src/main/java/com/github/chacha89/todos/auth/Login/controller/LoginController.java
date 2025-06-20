package com.github.chacha89.todos.auth.Login.controller;

import com.github.chacha89.todos.auth.Login.dto.requestDto.LoginRequestDto;
import com.github.chacha89.todos.auth.Login.dto.responseDto.LoginResponseDto;
import com.github.chacha89.todos.auth.Login.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class LoginController {
    //속
    private final LoginService loginService;


    //생

    public LoginController(LoginService loginService) {
        this.loginService = loginService;


    }

    //기
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> jwtAuthLoginAPI(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = loginService.JWTAuthLoginAPI(loginRequestDto);
        ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
        return response;
    }
}
