package com.github.chacha89.todos.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "object_id", nullable = false)
    private Long objectId;

    @Column(name = "object_type", nullable = false, length = 50)
    private String objectType;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "requestTime", nullable = false)
    private LocalDateTime requestTime;

    public Log() {}

    public Log(Long id, User user, Long objectId, String objectType, String activityType, String requestUrl, LocalDateTime requestTime) {
        this.id = id;
        this.user = user;
        this.objectId = objectId;
        this.objectType = objectType;
        this.activityType = activityType;
        this.requestUrl = requestUrl;
        this.requestTime = requestTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }
}
