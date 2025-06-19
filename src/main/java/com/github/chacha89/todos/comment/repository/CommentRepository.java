package com.github.chacha89.todos.comment.repository;

import com.github.chacha89.todos.comment.entity.Comment;
import com.github.chacha89.todos.todo.entity.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.todo.progress = :progress AND c.comment LIKE %:comment% AND c.isDeleted = false ORDER BY c.createdAt DESC")
    Page<Comment> findCommentsByProgressAndContent(@Param("progress") Progress progress,
                                                   @Param("comment") String comment,
                                                   Pageable pageable);




}
