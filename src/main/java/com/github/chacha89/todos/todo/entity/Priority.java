package com.github.chacha89.todos.todo.entity;

public enum Priority {
    LOW(1), MEDIUM(2), HIGH(3);
    private final int level;

    Priority(int level){
        this.level =level;
    }

    public int getLevel() {
        return level;
    }

// TodoService : valueOf() + exception handeling 사용 -> .isEnum 사용 안해도 됨
//    public boolean isEnum(String str){
//        for( Priority priority : Priority.values()){
//            if(priority.name().equals(str)){
//                return true;
//            }
//        }return false;
//    }

}
