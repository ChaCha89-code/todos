package com.github.chacha89.todos.config;

import com.github.chacha89.todos.jwt.service.JWTUtil;
import com.github.chacha89.todos.auth.logout.repository.BlacklistTokenRepository;
import com.github.chacha89.todos.security.filter.JWTFilter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JWTUtil jwtUtil;

    public WebConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Bean
    FilterRegistrationBean<Filter> addJWTFilter(BlacklistTokenRepository blacklistTokenRepository) {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JWTFilter(jwtUtil, blacklistTokenRepository));
        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
