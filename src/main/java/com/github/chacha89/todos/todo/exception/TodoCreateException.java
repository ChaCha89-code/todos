package com.github.chacha89.todos.todo.exception;

public class TodoCreateException extends RuntimeException {
  private int status;

  public TodoCreateException(int status, String message) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
