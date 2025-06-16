package com.github.chacha89.todos.security.filter;

import com.github.chacha89.todos.jwt.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JWTFilter implements Filter {

    private static final String[] WHITE_lIST = {"/teams", "/users", "/auth/login"};

    // 팀플을 위한 임시 비밀키
    private final String JWTSecretKey = "qweasd123456!@#$%^987654321ZXCASDQWE";
    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;


        // WhiteList에 추가된 URL{"/users", "/auth/login"}인지 검사
        if (isWhiteList(requestURI)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        } // ("/users", "/auth/login") 2개의 url이면 메서드 종료
        String jwtToken = httpRequest.getHeader("Authorization");
        // 토큰 부재시 400에러 응답
        if (jwtToken == null) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,"잘못된 토큰 입니다");
            return;
        }



    }

    /**
     *  WhiteList와 요청 url이 매치되는지 검증하는 로직
     * @param requestURI 요청한 url (/users, /auth/login ...)
     * @return WHITE_lIST = {"/", "/users", "/auth/login"} 리스트에 있다면 true, 없다면 false
     */
    public boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_lIST, requestURI);
    }

    // 토큰 검증 로직 ( 발급한 토큰이 맞나?)
    public Long verifyToken(String JWTToken) {
        // 시그니처키 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtService.createJWTKey().getBytes(StandardCharsets.UTF_8));

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
