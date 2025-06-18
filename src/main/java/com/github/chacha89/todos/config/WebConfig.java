package com.github.chacha89.todos.config;

import com.github.chacha89.todos.jwt.service.JWTService;
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
    private final JWTService jwtService;

    public WebConfig(JWTService jwtService) {
        this.jwtService = jwtService;
    }
    @Bean
    FilterRegistrationBean<Filter> addJWTFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JWTFilter(jwtService));
        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
