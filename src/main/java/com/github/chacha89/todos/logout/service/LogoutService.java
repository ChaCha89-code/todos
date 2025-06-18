package com.github.chacha89.todos.logout.service;

import com.github.chacha89.todos.jwt.service.JWTService;
import com.github.chacha89.todos.logout.entity.BlacklistToken;
import com.github.chacha89.todos.logout.repository.BlacklistTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutService {

    // 속성
    private final BlacklistTokenRepository blacklistTokenRepository;
    private final JWTService jwtService;



    // 생성자
    public LogoutService(BlacklistTokenRepository blacklistTokenRepository, JWTService jwtService) {
        this.blacklistTokenRepository = blacklistTokenRepository;
        this.jwtService = jwtService;
    }



    // 기능
    public void logout(String token) {

        // 1. 토큰 검증
        log.info("token: {}", token);
        BlacklistToken blacklistToken = new BlacklistToken(token);
        BlacklistToken saved = blacklistTokenRepository.save(blacklistToken);

    }

}
