package com.github.chacha89.todos.todo.entity;

public enum Progress {
    NotStarted(1), OnProgress(2), Completed(3);

    private final int step;

    Progress(int step){
        this.step =step;
    }

    public int getSteps() {
        return step;
    }

    public boolean isEnum(String str){
        for( Progress progress : Progress.values()){
            if(progress.name().equals(str)){
                return true;
            }
        }return false;
    }
}
