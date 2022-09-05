package com.example.auth.service;

import com.example.auth.client.UserClient;
import com.example.auth.client.vo.UserVO;
import com.example.auth.security.AuthRequest;
import com.example.auth.security.AuthResponse;
import com.example.auth.service.AuthService;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private final UserClient userClient;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(AuthRequest authRequest) {
//        UserVO userVO = userClient.registerUser(authRequest);
        UserVO userVO = restTemplate.postForObject("lb://user-service/users", authRequest, UserVO.class);
        Assert.notNull(userVO, "Failed to register user, please try again later");
        String accessToken = jwtUtil.generateToken(userVO, "ACCESS");
        String refreshToken = jwtUtil.generateToken(userVO, "REFRESH");
        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public AuthResponse login(AuthRequest authRequest){
        return null;
    }
}
