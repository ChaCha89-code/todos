package com.github.chacha89.todos.logout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "blacklist_token")
public class BlacklistToken {

    @Id
    @Column(length = 1000)
    private String token;

    public BlacklistToken() {}

    public BlacklistToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
