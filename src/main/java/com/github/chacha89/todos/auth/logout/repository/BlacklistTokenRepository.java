package com.github.chacha89.todos.auth.logout.repository;

import com.github.chacha89.todos.auth.logout.entity.BlacklistToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, String> {

    boolean existsByToken(String token);
}
