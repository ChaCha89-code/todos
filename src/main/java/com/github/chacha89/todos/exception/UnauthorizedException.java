package com.github.chacha89.todos.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(){
        super("잘못된 JWT 토큰 입니다");
    }
}
