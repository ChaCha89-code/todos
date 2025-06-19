package com.github.chacha89.todos.comment.exception;

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
