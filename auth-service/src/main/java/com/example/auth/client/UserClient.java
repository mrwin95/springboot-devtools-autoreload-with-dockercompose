package com.example.auth.client;

import com.example.auth.client.vo.UserVO;
import com.example.auth.security.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
@Component
public interface UserClient {

    @PostMapping("/users")
    public UserVO registerUser(@RequestBody AuthRequest authRequest);
}
