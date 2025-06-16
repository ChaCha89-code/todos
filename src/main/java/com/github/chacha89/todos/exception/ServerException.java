package com.github.chacha89.todos.exception;

public class ServerException extends RuntimeException{
    public ServerException() {
        super("토큰을 찾을 수 없습니다");
    }
}
