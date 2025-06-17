package com.github.chacha89.todos.exception;

public class CommentCreateException extends RuntimeException {
    private int status;

    public CommentCreateException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
