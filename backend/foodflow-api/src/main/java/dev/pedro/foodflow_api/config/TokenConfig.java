package dev.pedro.foodflow_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.pedro.foodflow_api.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    @Value("${JWT_SECRET}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create()
                .withClaim("user_id", user.getId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuedAt(Instant.now())
                .sign(Algorithm.HMAC256(secret));
    }

    public Optional<JwtUserData> validateToken(String token) {
        try {
            DecodedJWT decode = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return Optional.of(JwtUserData.builder()
                            .userId(decode.getClaim("userId").asLong())
                            .email(decode.getSubject())
                            .build());
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
