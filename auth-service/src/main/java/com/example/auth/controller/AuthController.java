package com.example.auth.controller;

import com.example.auth.security.AuthRequest;
import com.example.auth.security.AuthResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest){
        log.info("Inside register method of the AuthController");
        return ResponseEntity.ok(authService.register(authRequest));
    }

    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        log.info("Inside login");
        AuthResponse authRequest1 = new AuthResponse();
        return new ResponseEntity<>(authRequest1, HttpStatus.CREATED);
    }
}
