package com.example.gateway.filters;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    private List<String> openApis = List.of("/auth/login", "/auth/register");

    public Predicate<ServerHttpRequest> isSecured = request -> openApis.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
