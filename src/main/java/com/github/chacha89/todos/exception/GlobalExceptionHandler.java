package com.github.chacha89.todos.exception;

import com.github.chacha89.todos.comment.dto.CommentCreateErrorResponseDto;
import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.todo.dto.TodoCreateErrorResponseDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
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
    // 제대로 된 토큰이 아닐 경우 예외처리
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<APIErrorResponseDto> handleServerException(ServerException e)
    {
        APIErrorResponseDto ServerExceptionResponse
                = APIErrorResponseDto.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponseDto> ExceptionResponse
                = new ResponseEntity<>(ServerExceptionResponse, HttpStatus.BAD_REQUEST);
        return ExceptionResponse;
    }
    // 인증되지 않는 토큰을 검사시에 예외처리
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<APIErrorResponseDto> handleUnauthorizedException(UnauthorizedException e)
    {
        APIErrorResponseDto UnauthorizedResponse
                = APIErrorResponseDto.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponseDto> UnauthorizedExceptionResponse
                = new ResponseEntity<>(UnauthorizedResponse, HttpStatus.BAD_REQUEST);
        return UnauthorizedExceptionResponse;
    }

    // 할 일 작성 예외 처리
    @ExceptionHandler(TodoCreateException.class)
    public ResponseEntity<TodoCreateErrorResponseDto> handleTodoCreateException(TodoCreateException e) {
        return ResponseEntity.status(e.getStatus()).body(new TodoCreateErrorResponseDto(e.getStatus(), e.getMessage()));
    }

    // 커멘트 생성 예외 처리
    @ExceptionHandler(CommentCreateException.class)
    public ResponseEntity<CommentCreateErrorResponseDto> handleCommentCreateException(CommentCreateException e) {
        return ResponseEntity.status(e.getStatus()).body(new CommentCreateErrorResponseDto(e.getStatus(), e.getMessage()));
    }
    @ExceptionHandler(MissingSearchTermException.class)
    public ResponseEntity<APIErrorResponseDto> handleMissingSearchTermException(MissingSearchTermException e)
    {
        APIErrorResponseDto MissingSearchTermResponse
                = APIErrorResponseDto.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponseDto> MissingSearchTermExceptionResponse
                = new ResponseEntity<>(MissingSearchTermResponse, HttpStatus.BAD_REQUEST);
        return MissingSearchTermExceptionResponse;
    }
}
