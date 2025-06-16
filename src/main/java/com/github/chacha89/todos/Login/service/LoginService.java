package com.github.chacha89.todos.Login.service;

import com.github.chacha89.todos.Login.dto.requestDto.LoginRequestDto;
import com.github.chacha89.todos.Login.dto.responseDto.LoginResponseDto;
import com.github.chacha89.todos.exception.LoginEmailNotFoundException;
import com.github.chacha89.todos.exception.LoginInvalidPasswordException;
import com.github.chacha89.todos.jwt.service.JWTService;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

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
        String jwtKey = jwtService.createJWTKey(user, user.getId());
        return new LoginResponseDto(HttpStatus.OK,jwtKey);

    }
}
