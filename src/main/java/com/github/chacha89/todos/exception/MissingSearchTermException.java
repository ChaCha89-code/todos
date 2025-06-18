package com.github.chacha89.todos.exception;

public class MissingSearchTermException extends RuntimeException{
    public MissingSearchTermException(){
        super("검색어가 없습니다");
    }
}
