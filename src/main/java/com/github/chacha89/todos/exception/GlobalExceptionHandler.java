package com.github.chacha89.todos.exception;

import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.user.dto.responseDto.UserCreateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamCreateException.class)
    public ResponseEntity<TeamCreateResponseDto> handleTeamCreateException(TeamCreateException e) {
        return ResponseEntity.status(e.getStatus()).body(new TeamCreateResponseDto(e.getStatus(), e.getMessage()));
    }

    @ExceptionHandler(UserCreateException.class)
    public ResponseEntity<UserCreateResponseDto> handleUserCreateException(UserCreateException e) {
        return ResponseEntity.status(e.getStatus()).body(new UserCreateResponseDto(e.getStatus(), e.getMessage()));
    }
}
