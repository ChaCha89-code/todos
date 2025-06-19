package com.github.chacha89.todos.todo.repository;

import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.user.entity.User;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

   //유저삭제시 할일 미할당 할때 필요
    List<Todo> findByUser(User user);

    Long countByIsDeletedFalse();
    
    Page<Todo> findByContentContainingAndProgressOrderByUpdatedAtDesc(String content,
                                                                      Progress progress,
                                                                      Pageable pageable);
//    Page<Todo> findByTitleContainingAndContentContainingAndProgressOrderByUpdatedAtDesc(@Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.") String title, String content, Progress progress, Pageable pageable);
}
