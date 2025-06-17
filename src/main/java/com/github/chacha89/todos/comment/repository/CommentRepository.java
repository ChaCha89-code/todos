package com.github.chacha89.todos.comment.repository;

import com.github.chacha89.todos.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
