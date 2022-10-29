package com.example.auth.config;

import com.example.auth.filter.JwtFilter;
import com.example.auth.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

//    @Lazy
//    @Autowired
//    private LoginFilter loginFilter;
//    private final JwtFilter jwtFilter;
    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        uds.createUser(User.builder().username("thang").password("{noop}user").roles("USER").build());
        uds.createUser(User.builder().username("admin").password("{noop}user").roles("ADMIN","USER").build());
        return uds;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        var dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        return new ProviderManager(dao);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();
        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilterAt(loginFilter, BasicAuthenticationFilter.class);
//        http.addFilterAt(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
}
