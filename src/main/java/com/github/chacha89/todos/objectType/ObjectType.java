package com.github.chacha89.todos.objectType;

public enum ObjectType {
    TODO,
    COMMENT,
    USER
}

// 이 클래스가 없으면 로그 엔티티의 ObjectType이 enum이 아니라 외부 라이브러리의 클래스가 되서 오류가 발생할 수 있다.
// 오류 : Basic attribute type should not be 'ObjectType'
// 참고: JPA의 @Enumerated는 Java enum 타입에만 사용할 수 있습니다.