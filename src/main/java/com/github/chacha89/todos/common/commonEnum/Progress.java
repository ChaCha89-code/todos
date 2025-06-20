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
    // 사실 static으로 만들 이유는 크--게 있지는 않으나, 지금 수정하기엔 조금 늦어 냅둠니다
    public static Progress fromString(String str) {
        for (Progress p : values()) {
            if (p.strValue.equalsIgnoreCase(str)) {
                return p;
            }
        }
        // 일치하는 enum 이 없을 때 예외발생
        throw new IllegalArgumentException("일치하는 Enum이 없습니다 " + str);
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
