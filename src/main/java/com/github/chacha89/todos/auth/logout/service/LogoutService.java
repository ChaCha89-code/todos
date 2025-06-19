package com.github.chacha89.todos.auth.logout.service;

import com.github.chacha89.todos.jwt.service.JWTUtil;
import com.github.chacha89.todos.auth.logout.entity.BlacklistToken;
import com.github.chacha89.todos.auth.logout.repository.BlacklistTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutService {

    // 속성
    private final BlacklistTokenRepository blacklistTokenRepository;
    private final JWTUtil jwtUtil;



    // 생성자
    public LogoutService(BlacklistTokenRepository blacklistTokenRepository, JWTUtil jwtUtil) {
        this.blacklistTokenRepository = blacklistTokenRepository;
        this.jwtUtil = jwtUtil;
    }



    // 기능
    public void logout(String token) {

        // 1. 토큰 검증
        log.info("token: {}", token);
        BlacklistToken blacklistToken = new BlacklistToken(token);
        BlacklistToken saved = blacklistTokenRepository.save(blacklistToken);

    }

}
