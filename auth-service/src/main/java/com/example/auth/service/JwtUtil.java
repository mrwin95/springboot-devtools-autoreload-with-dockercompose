package com.example.auth.service;

import com.example.auth.client.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationToken}")
    private String expirationToken;
    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    public Date getExpirationDateFromToken(String token){
        return getAllClaimsFromToken(token).getExpiration();
    }

    public boolean isExpiredToken(String token){
        return this.getExpirationDateFromToken(token).before(new Date());
    }
    public String generateToken(UserVO userVO, String type){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userVO.getUsername());
        claims.put("type", type);
        claims.put("authorities", userVO.getAuthorities());
        return this.doGenerateToken(claims, userVO.getUsername(), type);
    }

    private String doGenerateToken(Map<String, Object> claims, String username, String type) {
        long expirationTimeLong;
        if("ACCESS".equals(type)){
            expirationTimeLong = Long.parseLong(expirationToken) * 1000;
        }else {
            expirationTimeLong = Long.parseLong(expirationToken) * 1000 * 5;
        }

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setExpiration(expirationDate)
                .setIssuedAt(createdDate)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        return !isExpiredToken(token);
    }
}
