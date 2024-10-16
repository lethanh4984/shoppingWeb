package com.example.demo.security;

import com.example.demo.config.JwtAuthenticationTokenFilter;
import com.example.demo.service.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final IUserService iUserService;
//
//    @Autowired
//    public SecurityConfig(IUserService iUserService) {
//        this.iUserService = iUserService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setUserDetailsService(iUserService);
//        dao.setPasswordEncoder(passwordEncoder()); // Use BCryptPasswordEncoder
//        return dao;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/customer/**").permitAll()
////                        .requestMatchers(HttpMethod.POST, "/api/customer/**").hasAnyRole("USER","ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
//                );
//
//                httpSecurity.httpBasic(Customizer.withDefaults());
//
//        httpSecurity.csrf(csrf -> csrf.disable());
//
//        return httpSecurity.build();
//    }
//}


@EnableWebSecurity
@Configuration
@EnableMethodSecurity()
public class SecurityConfig {
    @Autowired
    private IUserService userService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    //AuthenticationManager chịu trách nhiệm xác thực thông tin đăng nhập của người dùng.

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService((UserDetailsService) userService);
//        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
////                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(
//                        auth -> auth
//
//                                .requestMatchers("/api/login", "/api/customer/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/customer/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers(HttpMethod.POST, "/api/login").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
////                );
//
//                                .anyRequest().authenticated()
//                )
//                .build();
//    }

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