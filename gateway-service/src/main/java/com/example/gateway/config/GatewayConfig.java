package com.example.gateway.config;

import com.example.gateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Predicate;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service-id", r -> r.path("/auth-service/**").filters(f -> f.filter(filter))
//                                .circuitBreaker(config -> config.setName("auth-service-circuit-breaker").setFallbackUri("forward:/auth-service-fallback")))
                        .uri("lb://auth-service"))
                .route("user-service-id", r -> r.path("/user-service/**").filters(f -> f.filter(filter))
//                                .circuitBreaker(config -> config.setName("user-service-circuit-breaker").setFallbackUri("forward:/user-service-fallback")))
                        .uri("lb:/user-service"))
                .build();
    }


    @Bean @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
