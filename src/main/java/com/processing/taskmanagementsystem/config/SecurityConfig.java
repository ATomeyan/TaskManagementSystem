package com.processing.taskmanagementsystem.config;

import com.processing.taskmanagementsystem.config.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                        .httpBasic(AbstractHttpConfigurer::disable)
                            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/task/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/task-user").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/comment/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/user/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/task/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/comment/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/comment/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/task/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/task/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/comment/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/task/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}