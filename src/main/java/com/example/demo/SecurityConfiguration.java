// package com.example.demo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.demo.filters.*;
// import com.example.demo.implementations.JwtGenerator;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {
    
//     @Autowired
//     JwtGenerator jwtService;
    
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(authorize -> authorize
//                 .requestMatchers("/user").permitAll()
//                 .requestMatchers("/auth").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(new AuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
//             .build();
//     }
// }