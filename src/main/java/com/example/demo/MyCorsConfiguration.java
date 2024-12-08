package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyCorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000/**", "http://127.0.0.1:3000/**", "http://localhost:3000", "http://127.0.0.1:3000")
        .allowedHeaders("Authorization", "*")
        .allowCredentials(true)
        .allowedMethods(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"
        );
    }
}