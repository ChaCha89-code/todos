package com.github.chacha89.todos.activitylog.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ActivityLog {

    // 속성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private String activityType;

    @Column(nullable = false)
    private String requestUrl;

    @Column(nullable = false)
    private String requestMethod;

    @Column(nullable = false)
    private String requestIp;

    @Column(nullable = false, updatable = false)
    private LocalDateTime requestTime;

    // 생성자
    /**
     * 기본 생성자
     */
    public ActivityLog() {}

    public ActivityLog(Long id, Long userId, Long targetId, String activityType, String requestUrl, String requestMethod, String requestIp, LocalDateTime requestTime) {
        this.id = id;
        this.userId = userId;
        this.targetId = targetId;
        this.activityType = activityType;
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
        this.requestIp = requestIp;
        this.requestTime = requestTime;
    }


    // 기능
    /**
     * 로그 생성 시 requestTime 필드에 현재 시간 자동으로 넣어줌
     */
    @PrePersist
    public void prePersist() {
        this.requestTime = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
