package com.example.auth.controller;

import com.example.auth.security.AuthRequest;
import com.example.auth.security.AuthResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.register(authRequest));
    }
}