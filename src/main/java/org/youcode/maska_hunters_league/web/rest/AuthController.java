package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.web.VMs.SignUpVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.SignUpVMMapper;

@RestController
@RequestMapping("v1/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;
    private final SignUpVMMapper signUpVMMapper;

    public AuthController(AuthService authService, SignUpVMMapper signUpVMMapper) {
        this.authService = authService;
        this.signUpVMMapper = signUpVMMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid SignUpVM signUpVM) {
        User user = signUpVMMapper.toUser(signUpVM);
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
