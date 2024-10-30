package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.web.VMs.SignInVM;
import org.youcode.maska_hunters_league.web.VMs.SignUpVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.SignInVMMapper;
import org.youcode.maska_hunters_league.web.VMs.mapper.SignUpVMMapper;

@RestController
@RequestMapping("v1/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;
    private final SignUpVMMapper signUpVMMapper;
    private final SignInVMMapper signInVMMapper;

    public AuthController(AuthService authService, SignUpVMMapper signUpVMMapper, SignInVMMapper signInVMMapper) {
        this.authService = authService;
        this.signUpVMMapper = signUpVMMapper;
        this.signInVMMapper = signInVMMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid SignUpVM signUpVM) {
        User user = signUpVMMapper.toUser(signUpVM);
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid SignInVM signInVM) {
        User user = signInVMMapper.toUser(signInVM);

        User authenticatedUser = authService.login(user);

        if (authenticatedUser != null) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failed");
    }
}
