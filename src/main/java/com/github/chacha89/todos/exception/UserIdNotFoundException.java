package com.github.chacha89.todos.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserIdNotFoundException extends RuntimeException {
    private final HttpStatus status;
    //기본생성자 추가 >> 예외를 간퍈하게 던질 수 있음.
    public UserIdNotFoundException() {
        super("유저를 찾을 수가 없습니다.");
        this.status = HttpStatus.NOT_FOUND;
    }

    public UserIdNotFoundException(HttpStatus status){
        super("유저를 찾을 수가 없습니다.");
        this.status = status;
    }

}
