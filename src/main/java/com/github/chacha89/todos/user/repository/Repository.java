package com.github.chacha89.todos.user.repository;

import com.github.chacha89.todos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User,Long> {
}
