package com.github.chacha89.todos.comment.repository;

import com.github.chacha89.todos.comment.entity.Comment;
import com.github.chacha89.todos.todo.entity.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByProgressOrderByUpdatedAt(Progress progress, Pageable pageable);

    Page<Comment> findAllByCommentContaining(String comment, Pageable pageable);
    List<Comment> findByProgressAndCommentContainingOrderByUpdatedAtDesc(Progress progress, String comment, Pageable pageable);

}
