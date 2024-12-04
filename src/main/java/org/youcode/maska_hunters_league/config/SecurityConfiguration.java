package org.youcode.maska_hunters_league.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.youcode.maska_hunters_league.security.JwtAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.youcode.maska_hunters_league.domain.enums.Permission.CAN_MANAGE_COMPETITIONS;
import static org.youcode.maska_hunters_league.domain.enums.Permission.CAN_VIEW_COMPETITIONS;
import static org.youcode.maska_hunters_league.domain.enums.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                .requestMatchers("/api/v1/species/**").hasRole(ADMIN.name())

                .requestMatchers(GET, "/api/v1/competition/**").hasAuthority(CAN_VIEW_COMPETITIONS.name())
                .requestMatchers(POST, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())
                .requestMatchers(PUT, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())
                .requestMatchers(DELETE, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())

                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
