package org.youcode.maska_hunters_league.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.youcode.maska_hunters_league.security.JwtAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.youcode.maska_hunters_league.domain.enums.Permission.*;
import static org.youcode.maska_hunters_league.domain.enums.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String participationLink = "/api/v1/participation/**";
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(
                        authorizeHttpRequest -> {
                            authorizeHttpRequest.requestMatchers("/api/v1/auth/**")
                                    .permitAll();
                            authorizeHttpRequest.requestMatchers("/api/v1/hunt").hasAnyRole(ADMIN.name(), JURY.name());
                            authorizeHttpRequest.requestMatchers(POST,participationLink).hasAuthority(CAN_PARTICIPATE.name());
                            authorizeHttpRequest.requestMatchers(GET,participationLink).hasAuthority(CAN_VIEW_RANKINGS.name());
                            authorizeHttpRequest.requestMatchers(GET,participationLink).hasAuthority(CAN_VIEW_RANKINGS.name());
                            authorizeHttpRequest.requestMatchers("/api/v1/users/**").hasRole(ADMIN.name());
                            authorizeHttpRequest.anyRequest().authenticated();
                        }
                ).sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
