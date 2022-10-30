package com.example.gateway.filters;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {
    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Inside filter");
        ServerHttpRequest request = exchange.getRequest();
        if(routerValidator.isSecured.test(request)) {
            log.info("Valid request----");
            if(this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            final String token =  this.getAuthHeader(request);
            if(token == null){
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }
            if(StringUtils.isBlank(token)) {
                log.info("Token is blank: {} ", token);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            if(this.isAuthMissing(request)){
                log.info("Authorization header is missed {} ", this.isAuthMissing(request));
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }

            if(jwtUtil.isInvalidToken(token)){
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            }

            //this.populateRequestWithHeaders(exchange, token);
        }

        log.info("chain filter");
        return chain.filter(exchange);
    }

    public void populateRequestWithHeaders(ServerWebExchange exchange, String token){
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("username", String.valueOf(claims.get("username")))
                .header("role", String.valueOf(claims.get("role"))).build();
    }

    private String getAuthHeader(ServerHttpRequest request){
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public String getTokenFromRequest(ServerHttpRequest request){
        List<String> headers = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION);
        if(!headers.isEmpty()){
            String bearerToken = headers.get(0);
            if(org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
                return bearerToken.substring(7, bearerToken.length());
            }
        }

        return null;
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        log.info("isAuthMissing");
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }
}
