package com.github.chacha89.todos.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
/// / "/teams/**", "/users/**", "/todos/**", "/comments/**", "/logs/**","/auth/**"
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/teams/**", "/users/**","/auth/**", "/todos/**", "/comments/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form.disable())
//                .build();
//    }
//}

import com.github.chacha89.todos.jwt.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration           // 스프링 설정 파일이라는 뜻 설정용 Bean을 등록하는데 사용
@RequiredArgsConstructor //final 을 가진 필드를 생성자 만들어주는 어노테이션
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    //요청에 따러서 어떤 보안을 적용할지 적용하는 메서드
    @Bean
    public SecurityFilterChain securityFiltercChain(HttpSecurity http) throws Exception {
        http
                //CSRF 는 REST API라 비활성화
                .csrf(csrf -> csrf.disable())
                // 세션 안 쓰는 설정 (JWT 사용시 필수)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 🔑 요청 URL에 대한 인증/인가 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login", "/users", "/swagger-ui/**", "/v3/api-docs/**"
                        ).permitAll()  // 🔓 인증 없이 접근 허용
                        .anyRequest().authenticated() // 🔒 그 외 모든 요청은 인증 필요
                )
                // 🔄 필터 추가 (내가 만든 JWT 필터를 Spring Security 필터보다 먼저 실행)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // 🔧 폼 로그인/기본 로그인 비활성화 (우린 REST API만 쓰니까)
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.disable());

        return http.build();
    }

    // 📌 인증 관리자 등록 (나중에 비밀번호 로그인 추가할 경우 필요)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
