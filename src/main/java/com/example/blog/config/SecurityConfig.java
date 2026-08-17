package com.example.blog.config;

import com.example.blog.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 CSRF（前后端分离不需要）
                .csrf().disable()
                // 不使用 Session（JWT 不需要）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                // 公开接口：不需要登录
                .antMatchers("/api/articles/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/comments/**").permitAll()
                .antMatchers("/api/tags/**").permitAll()
                .antMatchers("/api/article-tags/**").permitAll()
                // 静态资源：不需要登录
                .antMatchers("/", "/index.html").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                // 其他所有接口需要登录
                .anyRequest().authenticated();

        return http.build();
    }
}