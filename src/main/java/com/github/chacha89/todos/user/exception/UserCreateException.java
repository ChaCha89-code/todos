package com.github.chacha89.todos.user.exception;

public class UserCreateException extends RuntimeException {
    private int status;

    public UserCreateException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
