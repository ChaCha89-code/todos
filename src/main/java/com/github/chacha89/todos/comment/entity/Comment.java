package com.github.chacha89.todos.comment.entity;

import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.user.entity.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;
    
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING) // enum 사용 명시
    @Column(name = "progress", nullable = false, length = 20)
    private Progress progress;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 기능
    public Comment() {}

    public Comment(User user, Todo todo, String comment) {
        this.user = user;
        this.todo = todo;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Todo getTodo() {
        return todo;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Progress getProgress() {
        return progress;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changeComment(String comment) {
        this.comment = comment;
    }

}
