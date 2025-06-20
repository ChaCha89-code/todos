package com.github.chacha89.todos.todo.repository;

import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

   //유저삭제시 할일 미할당 할때 필요
    List<Todo> findByUser(User user);


    Long countByIsDeletedFalse();
    
    Page<Todo> findByContentContainingAndProgressOrderByUpdatedAtDesc(String content,
                                                                      Progress progress,
                                                                      Pageable pageable);



    //전체 개수 세기, IsDeletedrk False인 것만
    List<Todo> findByProgressOrderByUpdatedAtDesc(Progress progress, Pageable pageable);





    Long countByProgress(Progress progress);

}
