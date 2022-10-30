package com.example.auth.util;

import com.example.auth.client.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public boolean isExpiredToken(String token){
        return this.getExpirationDateFromToken(token).before(new Date());
    }

    public String generateJwtToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(","));
         authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationToken) * 1000))
                .signWith(key)
                .compact();
    }
    public String generateToken(UserVO userVO, String type){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userVO.getUsername());
        claims.put("type", type);
        claims.put("roles", userVO.getAuthorities());
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

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isExpiredToken(token));
    }
}
