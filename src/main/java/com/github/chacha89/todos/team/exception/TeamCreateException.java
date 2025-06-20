package com.github.chacha89.todos.team.exception;

public class TeamCreateException extends RuntimeException {
    private int status;

    public TeamCreateException(int status, String message) {
        super(message); // ← 부모에게 메시지 전달! 부모 생성자 호출은 반드시 첫줄!
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
