package com.github.chacha89.todos.logout.service;

import com.github.chacha89.todos.logout.entity.BlacklistToken;
import com.github.chacha89.todos.logout.repository.BlacklistTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    // 속성
    private final BlacklistTokenRepository blacklistTokenRepository;



    // 생성자
    public LogoutService(BlacklistTokenRepository blacklistTokenRepository) {
        this.blacklistTokenRepository = blacklistTokenRepository;
    }



    // 기능
    public void logout(BlacklistToken blacklistToken) {
        BlacklistToken saved = blacklistTokenRepository.save(blacklistToken);
    }

}
