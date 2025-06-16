package com.github.chacha89.todos.exception;

import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.user.dto.responseDto.APIErrorResponseDto;
import com.github.chacha89.todos.user.dto.responseDto.UserCreateResponseDto;
import org.springframework.http.HttpStatus;
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
    // 로그인 시 잘못된 이메일을 입력 했을 때 예외처리
    @ExceptionHandler(LoginEmailNotFoundException.class)
    public ResponseEntity<APIErrorResponseDto> handleLoginMemberNotFoundException(LoginEmailNotFoundException e)
    {
        APIErrorResponseDto LoginMemberNotFoundResponse
                = APIErrorResponseDto.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());

        ResponseEntity<APIErrorResponseDto> LoginMemberNotFoundExceptionResponse
                = new ResponseEntity<>(LoginMemberNotFoundResponse, HttpStatus.BAD_REQUEST);
        return LoginMemberNotFoundExceptionResponse;
    }

    // 로그인 시 비밀번호가 다를 때 예외처리
    @ExceptionHandler(LoginInvalidPasswordException.class)
    public ResponseEntity<APIErrorResponseDto> handleLoginInvalidPasswordException(LoginInvalidPasswordException e)
    {
        APIErrorResponseDto LoginInvalidPasswordResponse
                = APIErrorResponseDto.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponseDto> LoginInvalidPasswordExceptionResponse
                = new ResponseEntity<>(LoginInvalidPasswordResponse, HttpStatus.BAD_REQUEST);
        return LoginInvalidPasswordExceptionResponse;
    }

}
