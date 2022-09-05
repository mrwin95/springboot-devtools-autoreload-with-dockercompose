package com.example.auth.service;

import com.example.auth.client.UserClient;
import com.example.auth.client.vo.UserVO;
import com.example.auth.security.AuthRequest;
import com.example.auth.security.AuthResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserClient userClient;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        UserVO userVO = userClient.registerUser(authRequest);

        return null;
    }

    public AuthResponse login(AuthRequest authRequest){
        return null;
    }
}
