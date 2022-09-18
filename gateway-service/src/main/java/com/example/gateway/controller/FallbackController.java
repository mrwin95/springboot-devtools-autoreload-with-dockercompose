package com.example.gateway.controller;

import com.example.gateway.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallbackController {

    @PostMapping("/auth-service-fallback")
    public ResponseEntity<?> AuthFallback(){
        return new ResponseEntity<>(ApiResponse.builder().success("false").message("User Auth Service is down! Please try later").build(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/user-service-fallback")
    public ResponseEntity<?> UserFallback(){
        return new ResponseEntity<>(ApiResponse.builder().success("false").message("User Service is down! Please try later").build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
