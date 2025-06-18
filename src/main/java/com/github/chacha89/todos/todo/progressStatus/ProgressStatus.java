package com.github.chacha89.todos.todo.progressStatus;

public enum ProgressStatus {
    TODO("todo"),
    INPROGRESS("inprogress"),
    DONE("done"),
    OVERDUE("overdue");
    private final String step;
    ProgressStatus(String step){
        this.step = step;
    }
    public String getStep() {
        return step;
    }
}
