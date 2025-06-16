package com.github.chacha89.todos.user.dto.responseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class APIResponse<T> {

    private boolean success;


    private int status;
    private String message;
    private T data;


    public APIResponse(boolean success, int status, String message, T data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }
    // 성공 응답 (data + message 선택 가능)
    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(true, 200, "OK", data);
    }
    public static <T> APIResponse<T> success(T data, String message) {
        return new APIResponse<>(true, 200, message, data);
    }
    // 실패 응답 (상태코드, 메시지, 데이터 선택 가능)
    public static <T> APIResponse<T> fail(int status, String message) {
        return new APIResponse<>(false, status, message, null);
    }

    public static <T> APIResponse<T> fail(int status, String message, T data) {
        return new APIResponse<>(false, status, message, data);
    }

}
