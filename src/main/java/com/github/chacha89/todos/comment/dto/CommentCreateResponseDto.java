package com.github.chacha89.todos.comment.dto;

public class CommentCreateResponseDto {
    private boolean success;
    private int status;
    private CommentData commentData;

    public CommentCreateResponseDto(boolean success, int status, CommentData commentData) {
        this.success = success;
        this.status = status;
        this.commentData = commentData;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public CommentData getCommentData() {
        return commentData;
    }
}
