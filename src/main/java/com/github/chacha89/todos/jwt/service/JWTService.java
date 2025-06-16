package com.github.chacha89.todos.jwt.service;

import com.github.chacha89.todos.exception.ServerException;
import com.github.chacha89.todos.exception.UnauthorizedException;
import com.github.chacha89.todos.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JWTService {
    private static final String BEARER_PREFIX = "Bearer ";
    // 생성, 검증, claims 추출에 사용하여 속성값에 사용
    private final SecretKey secretKey;
    // 환경변수로 설정한 키 가져오는 속성값
    public JWTService(@Value("${jwt.secret.key}")String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createJWTKey(User user, Long userId) {
        // 데이터 준비
        String subjectUser = userId.toString(); // 사용자준비
        Date now = new Date(); // 현재시간
//        Date expiration = new Date(now.getTime()+1000*60); // 만료시간 1분
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60); // 만료시간 60분

        // 토큰 제작

        String bearerJWTToken = BEARER_PREFIX + Jwts.builder()
                .subject(subjectUser)
                .issuedAt(now)
                .claim("role", "admin") // 사용자 권한
                .claim("email", user.getEmail()) // 사용자 이메일
                .claim("userName", user.getUserName()) // 사용자 이름
                .claim("team", user.getTeam()) // 사용자의 소속팀
                .claim("iat", now) // 토큰 발행 시간 표시
                .claim("exp", expiration)// 토큰 만료시간 표시
                .expiration(expiration) // 만료시간
                .signWith(secretKey, SignatureAlgorithm.HS256) //  HS256 알고리즘으로 토큰생성 그리고 알고리즘에 서명할 비밀 키
                .compact(); // 완료
        log.info("JWT토큰 {} ", bearerJWTToken);

        return bearerJWTToken;
    }

    // "Bearer 토큰"을 "토큰" 으로 반환시켜주는 로직
    public String substringToken(String bearerJWTToken) {
        /**
         * hasText - bearerJWTToken = null , ""(빈 문자열), " "(공백의 경우) false
         * startsWith - BEARER_PREFIX( bearer ) 시작이 아니면 false
         * 즉 둘중에 하나라도 false 발생시 예외 발생
         */
        if (StringUtils.hasText(bearerJWTToken) && bearerJWTToken.startsWith(BEARER_PREFIX)) {
            String substring = bearerJWTToken.substring(7);
            return substring;
        }

        throw new ServerException();
    }

    // 리프레시 토큰 제작
    public String createJWTRefreshToken(Long userId) {
        String subjectUser = userId.toString(); // 사용자준비
        Date now = new Date(); // 현재시간
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24 * 15); // 만료시간 15일

        // 토큰 제작

        String refreshBearerJWTToken = BEARER_PREFIX + Jwts.builder()
                .subject(subjectUser)
                .issuedAt(now)
                .claim("type", "refresh") // 리프레시 토큰인거 명시
                .claim("iat", now) // 토큰 발행 시간 표시
                .claim("exp", expiration)// 토큰 만료시간 표시
                .expiration(expiration) // 만료시간
                .signWith(secretKey, SignatureAlgorithm.HS256) //  HS256 알고리즘으로 토큰생성 그리고 알고리즘에 서명할 비밀 키
                .compact(); // 완료
        log.info("JWT토큰 {} ", refreshBearerJWTToken);

        return refreshBearerJWTToken;
    }

    /**
     * 발급한 토큰을 확인하고 검증
     *
     * @param JWTToken 반드시 "bearer"가 제거된 순수한 토큰이어야 함
     * @return 검증완료된 claims
     * 권한검증이 필요한 메서드( ex) 삭제) 에 반드시 필요
     * <p>
     * verifyToken 사용 방법
     * 발급한 토큰 검증 로직이 끝난 뒤에 검증 완료된 userId를 가져와야 함
     * <p>
     * 속성 - private final JWTService jwtService;
     * 생성자 - jwtService 추가
     * public 반환데이터타입 변수이름 (@RequestHeader("Authorization") String bearerToken , ???Id, 등)
     * { Claims claims = jwtService.verifyToken;
     * Long userId = Long.parseLong(claims.getSubject());
     * }
     * <p>
     * 추출한 userId를 적절한 위치(매개변수)에 삽입
     */
    public Claims verifyToken(String JWTToken) {
        // 토큰 검증 로직
        Claims claims;
        try {
            claims = Jwts.parser() // 토큰 분석 준비
                    .verifyWith(secretKey) // 비밀키 검증
                    .build() //파서(분석기) 제작
                    .parseSignedClaims(JWTToken) // 발급한 토큰을 분석, .claim을 검증하고 읽음 유효하면 claim 반환
                    .getPayload(); // claim 객체에서 페이로드 추출
            log.info("claims {} ", claims);
            return claims;
        }
        // claims 을 검증 했을 때 유효하지 못한 경우 예외처리
        catch (JwtException e) {
            throw new UnauthorizedException();
        }

    }

}

