package com.github.chacha89.todos.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
@Slf4j
public class JwtAuthenticationFilter {
    /**
     * 발급된 토큰을 검사하는 로직입니다
     * @param JWTToken 발급된 토큰
     * @return 토큰의 검증이 완료된 userId를 반환
     */
    // 팀플을 위한 임시 비밀키
    private String JWTSecretKey = "qweasd123456!@#$%^987654321ZXCASDQWE";

    public Long verifyToken(String JWTToken) {
        // 시그니처키 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(JWTSecretKey.getBytes(StandardCharsets.UTF_8));

        // 토큰 검증 로직
        Claims claims = Jwts.parser() // 토큰 분석 준비
                .verifyWith(secretKey) // 비밀키 검증
                .build() //파서(분석기) 제작
                .parseSignedClaims(JWTToken) // 발급한 토큰을 분석, .claim을 검증하고 읽음 유효하면 claim 반환
                .getPayload(); // claim 객체에서 페이로드 추출
        log.info("claims {} " ,claims);

        // 사용자 추출
        String subjectUser = claims.getSubject();
        log.info("subjectUser {} ", subjectUser);

        // 타입 변환
        long userId = Long.parseLong(subjectUser);
        log.info("userID {} ",userId);

        return userId;
    }
}
