package com.example.ScamNumbers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);//default
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for Postman testing
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                /*
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users").permitAll()  // public endpoint
                        .requestMatchers("/numbers").permitAll()
                        .requestMatchers("/reports").permitAll()
                        .requestMatchers("/search").permitAll()
                        //.requestMatchers("/admin/**").authenticated() // secured
                        .anyRequest().permitAll() // everything else public
                )*/
                .httpBasic(httpBasic -> {
                }); // or formLogin()

        return http.build();
    }
}
