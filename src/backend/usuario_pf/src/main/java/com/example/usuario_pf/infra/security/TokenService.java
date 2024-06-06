package com.example.usuario_pf.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.usuario_pf.model.entity.UsuarioPFEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

       private byte[] byte_secret;

    @PostConstruct
    public void init() {
        byte_secret = secret.getBytes();
    }

    public String generateToken(UsuarioPFEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    
    public Claims verify(String authorization) {
        try {
            Claims claims = Jwts.parser().setSigningKey(byte_secret).parseClaimsJws(authorization).getBody();

            return claims;
        } catch (Exception e) {
            throw new RuntimeException("Error while verifying token", e);
        }
    }

    public Integer extractUserIdFromToken(String authorization) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(byte_secret)
                    .parseClaimsJws(authorization)
                    .getBody();

            // Extrai o user_id como String
            Integer userId = claims.get("id", Integer.class);
            return userId;
        } catch (Exception e) {
            throw new RuntimeException("Error while verifying token", e);
        }

    }

    public String extractLoginFromToken(String authorization) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(byte_secret)
                    .parseClaimsJws(authorization)
                    .getBody();

            // Extrai o user_id como String
            String login = claims.get("sub", String.class);
            return login;
        } catch (Exception e) {
            throw new RuntimeException("Error while verifying token", e);
        }

    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}