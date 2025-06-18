package com.github.chacha89.todos.todo.repository;

import com.github.chacha89.todos.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user WHERE t.progress = :progress AND t.user.userName = :userUserName")
    // 위 쿼리문은 쿼리문 없이 적용해보고 적용 예정
    Page<Todo> findByProgressAndUser_UserNameOrderByUpdatedAtDesc(String progress, String userUserName, Pageable pageable);

}
