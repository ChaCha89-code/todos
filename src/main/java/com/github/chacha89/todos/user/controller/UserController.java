package com.github.chacha89.todos.user.controller;

import com.github.chacha89.todos.user.dto.requestDto.UserCreateRequestDto;
import com.github.chacha89.todos.user.dto.responseDto.UserCreateResponseDto;
import com.github.chacha89.todos.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Controller(권장): 예외 처리 또는 ResponseEntity 반환
@RestController
@RequestMapping("/users")
public class UserController {
    // 속성
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원 가입 API
     * @param requestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUserAPI(@ModelAttribute UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUserService(requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
