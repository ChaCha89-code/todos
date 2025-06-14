package com.github.chacha89.todos.domain;

import com.github.chacha89.todos.user.entity.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@EntityListeners(AuditingEntityListener.class)
public class Todo {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // user != assignee
    @Column(name = "assignee", nullable = false, length = 50)
    private String assignee;

    @Column(name = "image")
    private String image;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "priority", nullable = false, length = 20)
    private String priority;

    @Column(name = "progress", nullable = false, length = 20)
    private String progress;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDate today;

    @Column
    private LocalDate dueDate;


    // 생성자
    public Todo() {}

    public Todo(Long id, User user, String assignee, String image, String content, String priority, String progress, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate today, LocalDate dueDate) {
        this.id = id;
        this.user = user;
        this.assignee = assignee;
        this.image = image;
        this.content = content;
        this.priority = priority;
        this.progress = progress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.today = today;
        this.dueDate = dueDate;
    }

    // 기능

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getPriority() {
        return priority;
    }

    public String getProgress() {
        return progress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getToday() {
        return today;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

}

// 만약 assignee도 실제 회원(User)라면 User 타입으로 맵핑하는 것이 더 강력하고 안전합니다
// @ManyToOne
// @JoinColumn(name = "assignee_id", nullable = false)
    // 장점
    // private User assignee;
    // 정확한 사용자 ID 추적 가능
    // 나중에 userName, image 등도 쉽게 접근 가능 (todo.getAssignee().getUserName())
    // 단점
    // 때로는 이름만 남기고 싶은 경우에는 과할 수 있음
// 결론:
// 현재처럼 user는 User 엔티티, assignee는 단순한 이름(String)으로 처리하는 것도 완전히 괜찮습니다.
// 단, assignee도 등록된 사용자일 가능성이 높다면 User로 바꾸는 것이 더 정규화된 설계입니다.
