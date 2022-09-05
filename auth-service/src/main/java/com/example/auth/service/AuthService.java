package com.example.auth.service;

import com.example.auth.security.AuthRequest;
import com.example.auth.security.AuthResponse;

public interface AuthService {

    public AuthResponse register(AuthRequest authRequest);

    public AuthResponse login(AuthRequest authRequest);
}
