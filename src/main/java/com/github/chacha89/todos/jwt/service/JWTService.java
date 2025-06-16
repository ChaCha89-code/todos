package com.github.chacha89.todos.jwt.service;

import com.github.chacha89.todos.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JWTService {
    /**
     * 윈도우 검색창에 '환경 변수' 또는 '환경 변수 편집' 입력
     *
     * ‘시스템 환경 변수 편집’을 선택
     * (일반적으로 "이 PC" 오른쪽 클릭 → "속성" → "고급 시스템 설정" → "환경 변수(N)" 버튼 클릭)
     *
     * 환경 변수 창이 뜨면, 아래쪽 ‘시스템 변수(S)’ 또는 ‘사용자 변수(U)’에서 새로 만들기!
     *
     * 새 변수 만들기
     *
     * 이름(N): JWT_SECRET_KEY
     * 값(V): qweasd123456!@#$%^987654321ZXCASDQWE
     * 확인 후 창 닫기
     *
     * 모든 창에서 ‘확인’ 누르기.
     */
    // secretKey = qweasd123456!@#$%^987654321ZXCASDQWE
//    private String JWTSecretKey = System.getenv("JWT_SECRET_KEY"); // 환경변수로 설정한 키 가져오는 속성값
    private String JWTSecretKey = "qweasd123456!@#$%^987654321ZXCASDQWE";


    public String createJWTKey(User user,Long userId) {
        // StandardCharsets.UTF_8 표준 문자셋
        // 시그니처키 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(JWTSecretKey.getBytes(StandardCharsets.UTF_8));

        // 데이터 준비
        String subjectUser = userId.toString(); // 사용자준비
        Date now = new Date(); // 현재시간
        Date expiration = new Date(now.getTime()+1000*60); // 만료시간 1분
//        Date expiration = new Date(now.getTime()+1000*900); // 만료시간 15분

        // 토큰 제작
        String JWTToken = Jwts.builder()
                .subject(subjectUser)
                .issuedAt(now)
                .claim("role","admin") // 사용자 권한
                .claim("email",user.getEmail()) // 사용자 이메일
                .claim("userName",user.getUserName()) // 사용자 이름
                .claim("team",user.getTeam()) // 사용자의 소속팀
                .claim("iat",now) // 토큰 발행 시간 표시
                .claim("exp",expiration)// 토큰 만료시간 표시
                .expiration(expiration) // 만료시간
                .signWith(secretKey, SignatureAlgorithm.HS256) //  HS256 알고리즘으로 토큰생성 그리고 알고리즘에 서명할 비밀 키
                .compact(); // 완료
        log.info("JWT토큰 {} ", JWTToken);

        return JWTToken;
    }

}
