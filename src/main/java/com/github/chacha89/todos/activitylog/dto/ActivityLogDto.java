package com.github.chacha89.todos.activitylog.dto;

import java.time.LocalDateTime;

public class ActivityLogDto {

    // 속성
    private Long userId;
    private Long targetId;
    private String activityType;
    private String requestUrl;
    private String requestMethod;
    private String requestIp;
    private LocalDateTime requestTime;


    // 생성자
    public ActivityLogDto(Long userId, Long targetId, String activityType, String requestUrl, String requestMethod, String requestIp, LocalDateTime requestTime) {
        this.userId = userId;
        this.targetId = targetId;
        this.activityType = activityType;
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
        this.requestIp = requestIp;
        this.requestTime = requestTime;
    }


    // 기능
    public Long getUserId() {
        return userId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }
}
