package com.example.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/token")
@Slf4j
@RequiredArgsConstructor
public class TokenProviderController {

    @GetMapping("/validateToken/{token}")
    public void validateToken(@Valid @PathVariable String token){

    }

    @GetMapping("/getUsernameFromToken/{token}")
    public void getUsernameFromToken(@Valid @PathVariable String token){

    }
}
