package com.example.demo.security;

import com.example.demo.service.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securityConfig {

    @Autowired
    private final IUserService iUserService;


    public securityConfig(IUserService iUserService) {
        this.iUserService = iUserService;
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setUserDetailsService(iUserService);
////        dao.setPasswordEncoder(passwordEncoder());
//        dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return dao;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/customer/**").permitAll() // Cho phép truy cập không cần đăng nhập
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ ROLE_ADMIN mới có thể truy cập vào trang /admin
                );
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}

