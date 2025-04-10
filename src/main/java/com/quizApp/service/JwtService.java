package com.quizApp.service;

import com.quizApp.entity.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String jwtsecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtsecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Admin admin) {
        return Jwts.builder()
                .subject(admin.getId().toString())
                .claim("email",admin.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))//10 min
                .signWith(getSecretKey())
                .compact();
    }



}
