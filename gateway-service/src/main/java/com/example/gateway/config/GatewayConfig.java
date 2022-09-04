package com.example.gateway.config;

import com.example.gateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service-id", r -> r.path("/auth/**").filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("user-service-id", r -> r.path("/users/**").filters(f -> f.filter(filter))
                        .uri("lb:/user-service"))
                .build();
    }
}
