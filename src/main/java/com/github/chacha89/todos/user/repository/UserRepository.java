package com.github.chacha89.todos.user.repository;

import com.github.chacha89.todos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 유저서비스의 회원 가입 기능에서 이메일 중복에 사용됨
    // Spring Data JPA의 쿼리 메서드 기능
    // existsBy[필드명] 형식을 자동 인식해서 SQL 쿼리를 생성
    boolean existsByEmail(String email);

    // 로그인 시 삭제된 유저 포함해서 조회 (중요)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmailIncludeDeleted(@Param("email") String email);

    //소프트 삭제
    Optional<User> findByIdAndDeletedFalse(Long id); // 단건 조회용


    Optional<User> findByEmail(String email);


}


