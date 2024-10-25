package com.example.demo.security;

import com.example.demo.config.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity()
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    //AuthenticationManager chịu trách nhiệm xác thực thông tin đăng nhập của người dùng.

    @Bean()
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //tao bean dung de ma hoa cho password
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)

                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/login","/api/customer/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/admin/").authenticated()
                )
                .build();
    }
}