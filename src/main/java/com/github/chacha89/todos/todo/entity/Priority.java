package com.github.chacha89.todos.todo.entity;

public enum Priority {
    Low(1), Medium(2), High(3);
    private final int level;

    Priority(int level){
        this.level =level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isEnum(String str){
        for( Priority priority : Priority.values()){
            if(priority.name().equals(str)){
                return true;
            }
        }return false;
    }
}
