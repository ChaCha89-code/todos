package com.github.chacha89.todos.todo.entity;

public enum Progress {
    NotStarted(1), InProgress(2), Completed(3);

    private final int step;

    Progress(int step){
        this.step =step;
    }

    public int getSteps() {
        return step;
    }

// TodoService : valueOf() + exception handeling 사용 -> .isEnum 사용 안해도 됨
//    public boolean isEnum(String str){
//        for( Progress progress : Progress.values()){
//            if(progress.name().equals(str)){
//                return true;
//            }
//        }return false;
//    }

}
