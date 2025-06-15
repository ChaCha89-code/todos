package com.github.chacha89.todos.domain;

import com.github.chacha89.todos.objectType.ObjectType;
import com.github.chacha89.todos.user.entity.User;
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

    // 단순 문자열로 저장하면 오타나 오류 발생할 수 있음.
    // ObjectType의 올바른 import: import com.github.chacha89.todos.domain.ObjectType; (직접 정의한 enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "object_type", nullable = false)
    private ObjectType objectType;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "requestTime", nullable = false)
    private LocalDateTime requestTime;

    public Log() {}

    public Log(Long id, User user, Long objectId, ObjectType objectType, String activityType, String requestUrl, LocalDateTime requestTime) {
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

    public ObjectType getObjectType() {
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
