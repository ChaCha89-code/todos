package com.github.chacha89.todos.exception;

public class LoginInvalidPasswordException extends RuntimeException{
    public LoginInvalidPasswordException () {
        super("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}
