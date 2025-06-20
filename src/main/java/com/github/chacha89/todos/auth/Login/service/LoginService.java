package com.github.chacha89.todos.auth.Login.service;

import com.github.chacha89.todos.auth.Login.dto.requestDto.LoginRequestDto;
import com.github.chacha89.todos.auth.Login.dto.responseDto.LoginResponseDto;
import com.github.chacha89.todos.auth.exception.LoginEmailNotFoundException;
import com.github.chacha89.todos.auth.exception.LoginInvalidPasswordException;
import com.github.chacha89.todos.jwt.service.JWTUtil;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Transactional
    public LoginResponseDto JWTAuthLoginAPI(LoginRequestDto loginRequestDto) {
        // 사용자 조회
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new LoginEmailNotFoundException());

        // 입력한 비밀번호와 기존 비밀번호 맞는지 확인
        boolean passwordMatches
                = passwordEncoder
                .matches(loginRequestDto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new LoginInvalidPasswordException();
        }
        // 로그인 성공 후 토큰 발급
        String jwtKey = jwtUtil.createJWTKey(user, user.getId());
        return new LoginResponseDto(HttpStatus.OK,jwtKey);

    }
}
