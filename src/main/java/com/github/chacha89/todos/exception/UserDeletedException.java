package com.github.chacha89.todos.exception;

public class UserDeletedException extends RuntimeException{
    public UserDeletedException(String message) {
        super(message);
    }
}
