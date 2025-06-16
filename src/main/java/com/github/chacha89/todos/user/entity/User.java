package com.github.chacha89.todos.user.entity;

import com.github.chacha89.todos.team.entity.Team;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    // 반드시 암호화(해시) 후 저장: new BCryptPasswordEncoder().encode(password)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User() {}

    //
    public User(Team team, String userName, String email, String password, String userImage) {
        this.team = team;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userImage = userImage;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeUserImage(String userImage) {
        this.userImage = userImage;
    }
}
