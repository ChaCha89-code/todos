package com.github.chacha89.todos.todo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Progress {
    TODO(1), INPROGRESS(2), DONE(3);

    Todo("Todo",1),
    InProgress("InProgress",2),
    Done("Done",3),
    OverDue("OverDue",4);
    private final String strValue;
    private final int step;

    Progress(String strValue, int step) {
        this.strValue = strValue;
        this.step = step;
    }
    // @Request
    public static Progress fromString(String str) {
        for (Progress p : values()) {
            if (p.strValue.equalsIgnoreCase(str)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid progress: " + str);
    }




    public String getStrValue() {
        return strValue;
    }

    public int getStep() {
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
