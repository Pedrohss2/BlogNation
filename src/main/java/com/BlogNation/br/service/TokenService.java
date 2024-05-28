package com.BlogNation.br.service;

import com.BlogNation.br.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("blog-nation-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generationExpirationToken())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException error) {
            throw new RuntimeException("Error while generate token: " + error);
        }
    }

    public String validate(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("blog-nation-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException error) {
            return "";
        }
    }

    private Instant generationExpirationToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
