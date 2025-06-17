package com.github.chacha89.todos.todo.repository;

import com.github.chacha89.todos.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
