package org.youcode.maska_hunters_league.service.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.domain.enums.Role;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.security.JwtService;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.RegisterRequest;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthService {

    private final UserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .cin(request.getCin())
                .nationality(request.getNationality())
                .role(Role.valueOf(request.getRole()))
                .joinDate(LocalDateTime.now())
                .build();

        appUserRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
