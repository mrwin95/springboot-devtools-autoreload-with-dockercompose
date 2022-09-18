package com.example.auth.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");

        var authenticated  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        response.setHeader(HttpHeaders.AUTHORIZATION, username);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var method = request.getMethod();
        var url = request.getRequestURI();
        var isLogin = HttpMethod.POST.matches(method) && url.startsWith("/auth/login");
        return !isLogin;
    }
}
