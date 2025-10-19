package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth.html", "/api/auth/**", "/favicon.ico").permitAll() // ← Allow all auth endpoints + favicon
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())           // Disable CSRF for simplicity
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic Auth
                .formLogin(formLogin -> formLogin.disable()); // Disable default login form

        return http.build();
    }
}