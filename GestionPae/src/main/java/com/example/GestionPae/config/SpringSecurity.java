package com.example.GestionPae.config;

import com.example.GestionPae.config.filter.JwtAuthenticationFilter;
import com.example.GestionPae.config.filter.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/api/logistics/**").hasAuthority("USER_LOGISTICS")
                        .requestMatchers("/api/school/**").hasAuthority("USER_SCHOOL")
                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtValidationFilter(authManager));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
