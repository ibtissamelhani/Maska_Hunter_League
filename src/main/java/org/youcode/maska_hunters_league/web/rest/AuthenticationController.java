package org.youcode.maska_hunters_league.web.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.service.Implementations.AuthenticationService;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.RegisterRequest;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest  request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
