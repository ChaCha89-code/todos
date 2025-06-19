package com.github.chacha89.todos.exception;
//비밀번호 검증시 에러
public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message) {
        super(message);
    }
}
