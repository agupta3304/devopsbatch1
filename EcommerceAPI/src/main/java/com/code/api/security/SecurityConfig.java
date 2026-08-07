package com.code.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                //PUBLIC AUTH APIs
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/category/**").permitAll()
                // CATEGORY VIEW (USER + ADMIN)
						/*
						 * .requestMatchers(HttpMethod.GET, "/api/category/**") .hasAnyRole("USER",
						 * "ADMIN")
						 */
                 // CATEGORY MODIFY (ADMIN ONLY)
                    .requestMatchers(HttpMethod.POST, "/api/users/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**")
                        .hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                        .hasRole("ADMIN")
                // CATEGORY MODIFY (ADMIN ONLY)
						
						/*
						 * .requestMatchers(HttpMethod.POST, "/api/category/**") .hasRole("ADMIN")
						 * 
						 * .requestMatchers(HttpMethod.PUT, "/api/category/**") .hasRole("ADMIN")
						 */
                .requestMatchers(HttpMethod.DELETE, "/api/category/**")
                    .hasRole("ADMIN")

                //EVERYTHING ELSE MUST BE AUTHENTICATED
                .anyRequest().authenticated()
            );

        //ENABLE JWT FILTER
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // REQUIRED for /auth/login
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
