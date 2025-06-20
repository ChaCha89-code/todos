package com.github.chacha89.todos.security.filter;




import com.github.chacha89.todos.jwt.service.JWTUtil;
import com.github.chacha89.todos.auth.logout.repository.BlacklistTokenRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;


import java.io.IOException;
import java.util.Arrays;


@Slf4j
public class JWTFilter implements Filter {
    private final JWTUtil jwtUtil;

    private final BlacklistTokenRepository blacklistTokenRepository;

    public JWTFilter(JWTUtil jwtUtil, BlacklistTokenRepository blacklistTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.blacklistTokenRepository = blacklistTokenRepository;
    }

    private static final String[] WHITE_lIST = {"/teams","/users" ,"/users/**", "/auth/login","/todos/","/todos/allCount", "/todos/*"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        log.info("WHITE_lIST {}", Arrays.toString(WHITE_lIST));

        // WhiteList에 추가된 URL{"/users", "/auth/login"}인지 검사
        if (isWhiteList(requestURI)){
            filterChain.doFilter(servletRequest,servletResponse);
            // ("/users", "/auth/login") 2개의 url이면 메서드 종료
            return;
        }

        String bearJWTToken = httpRequest.getHeader("Authorization");
        // 토큰 부재시 400에러 응답
        if (bearJWTToken == null || !bearJWTToken.startsWith("Bearer")) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,"잘못된 토큰 입니다");
            return;
        }

        String token = bearJWTToken.substring(7);

        if (blacklistTokenRepository.existsByToken(token)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그아웃된 토큰입니다.");
            return;
        }

        try {
            jwtUtil.verifyToken(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "잘못된 토큰입니다.");

        }

    }

    /**
     *  WhiteList와 요청 url이 매치되는지 검증하는 로직
     * @param requestURI 요청한 url (/users, /auth/login ...)
     * @return WHITE_lIST = {"/", "/users", "/auth/login"} 리스트에 있다면 true, 없다면 false
     */
    public boolean isWhiteList(String requestURI) {

        boolean simpleMatch = PatternMatchUtils.simpleMatch(WHITE_lIST, requestURI);
        //.simpleMatch 정규표현식이냐 문자열표현이냐
        log.info("WHITE_LIST {} requsetURI {}", WHITE_lIST,requestURI);
        log.info("simpleMatch {}", simpleMatch);
        return simpleMatch;

    }

}
