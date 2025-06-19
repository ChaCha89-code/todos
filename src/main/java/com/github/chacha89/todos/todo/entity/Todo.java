package com.github.chacha89.todos.todo.entity;

import com.github.chacha89.todos.common.commonEnum.Priority;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // user != assignee
    @Column(name = "assignee", nullable = false, length = 50)
    private String assignee;

    @Column(name = "title", nullable = false, length = 150)
    @Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING) // enum 사용 명시
    @Column(name = "priority", nullable = false, length = 20)
    private Priority priority;

    @Enumerated(EnumType.STRING) // enum 사용 명시
    @Column(name = "progress", nullable = false, length = 20)
    private Progress progress;

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

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;


    // 생성자
    protected Todo() {}

    public Todo(User user, String assignee, String title, String image, String content, Priority priority, Progress progress, LocalDate dueDate) {
        this.user = user;
        this.assignee = assignee;
        this.title = title;
        this.image = image;
        this.content = content;
        this.priority = priority;
        this.progress = progress;
        this.dueDate = dueDate;
    }

// 기능

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
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

    public Priority getPriority() {
        return priority;
    }

    public Progress getProgress() {
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

    public void changeAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeImage(String image) {
        this.image = image;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    public void changeProgress(Progress progress) {
        this.progress = progress;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }


}
