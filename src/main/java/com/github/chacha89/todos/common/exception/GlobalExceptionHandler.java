package com.github.chacha89.todos.common.exception;

import com.github.chacha89.todos.activitylog.exception.ActivityLogNotfoundException;
import com.github.chacha89.todos.auth.exception.LoginEmailNotFoundException;
import com.github.chacha89.todos.auth.exception.LoginInvalidPasswordException;
import com.github.chacha89.todos.auth.exception.PasswordMismatchException;
import com.github.chacha89.todos.comment.dto.CommentCreateErrorResponseDto;
import com.github.chacha89.todos.comment.exception.CommentCreateException;
import com.github.chacha89.todos.jwt.exception.StringIndexOutOfBoundsException;
import com.github.chacha89.todos.jwt.exception.UnauthorizedException;
import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.team.exception.TeamCreateException;
import com.github.chacha89.todos.todo.dto.response.TodoCreateErrorResponseDto;

import com.github.chacha89.todos.todo.exception.MissingSearchTermException;
import com.github.chacha89.todos.todo.exception.TodoCreateException;
import com.github.chacha89.todos.common.responseDto.APIResponse;
import com.github.chacha89.todos.user.dto.responseDto.UserCreateResponseDto;
import com.github.chacha89.todos.user.exception.UserCreateException;
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
    public ResponseEntity handleLoginMemberNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(400,"아이디 또는 비밀번호가 일치하지 않습니다."));
    }

    // 로그인 시 비밀번호가 다를 때 예외처리
    @ExceptionHandler(LoginInvalidPasswordException.class)
    public ResponseEntity handleLoginInvalidPasswordException()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(400,"아이디 또는 비밀번호가 일치하지 않습니다."));
    }
    // 제대로 된 토큰이 아닐 경우 예외처리
    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public ResponseEntity handleServerException()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(400,"토큰을 찾을 수 없습니다"));
    }
    // 인증되지 않는 토큰을 검사시에 예외처리
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleUnauthorizedException()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(400,"잘못된 JWT 토큰 입니다"));
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
    // 게시글 조회시 검색어가 없을 때 예외처리
    @ExceptionHandler(MissingSearchTermException.class)
    public ResponseEntity handleMissingSearchTermException()
    {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(400,"검색어가 없습니다"));}

    //비밀번호 검증
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<APIResponse<Void>>handlePasswordMismatchException(){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(APIResponse.fail(401,"비밀번호가 일치하지 않습니다.",null));

    }

    @ExceptionHandler(ActivityLogNotfoundException.class)
    public ResponseEntity<APIResponse> handleActivityLogNotFound(ActivityLogNotfoundException activityLogNotfoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.fail(404, "활동 로그가 없습니다."));
    }


}
