package com.crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter) {

        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()

                        .requestMatchers("/api/dashboard/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers("/api/contacts/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers("/api/tasks/**")
                        .hasAnyRole("ADMIN","MANAGER","SALES")

                        .requestMatchers("/api/leads/**")
                        .hasAnyRole("ADMIN","MANAGER","SALES")

                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}