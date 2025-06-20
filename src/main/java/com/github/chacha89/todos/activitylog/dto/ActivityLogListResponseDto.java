package com.github.chacha89.todos.activitylog.dto;

import java.util.List;

public class ActivityLogListResponseDto {

    private int status;
    private String message;
    private List<ActivityLogDto> acTivityLogList;

    public ActivityLogListResponseDto(int status, String message, List<ActivityLogDto> acTivityLogList) {
        this.status = status;
        this.message = message;
        this.acTivityLogList = acTivityLogList;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ActivityLogDto> getAcTivityLogList() {
        return acTivityLogList;
    }
}
