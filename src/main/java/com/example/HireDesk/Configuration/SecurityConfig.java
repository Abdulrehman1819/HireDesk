package com.example.HireDesk.Configuration;

import com.example.HireDesk.Util.JWTFilter;
import com.example.HireDesk.model.CustomUserDetailService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
    @Configuration

public class SecurityConfig {
@Autowired
private JWTFilter jwtFilter;
    @Bean
    public SecurityFilterChain doFilterChain(HttpSecurity httpSecurity){

    httpSecurity
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/auth/signup","/api/auth/login").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomUserDetailService customUserDetailService() {
        return new CustomUserDetailService();
    }
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, CustomUserDetailService customUserDetailService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(daoAuthenticationProvider);
    }
}
