package com.github.chacha89.todos.common.commonEnum;

public enum Progress {


    TODO("TODO", 1),
    INPROGRESS("INPROGRESS", 2),
    DONE("DONE", 3),
    OVERDUE("OVERDUE", 4);
    private final String strValue;
    private final int steps;

    Progress(String strValue, int step) {
        this.strValue = strValue;
        this.steps = step;
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

    public int getSteps() {
        return steps;
    }
}
// TodoService : valueOf() + exception handeling 사용 -> .isEnum 사용 안해도 됨
//    public boolean isEnum(String str){
//        for( Progress progress : Progress.values()){
//            if(progress.name().equals(str)){
//                return true;
//            }
//        }return false;
//    }
