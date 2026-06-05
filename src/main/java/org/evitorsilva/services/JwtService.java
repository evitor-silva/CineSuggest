package org.evitorsilva.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String tokenSecret;
    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(tokenSecret);
    }

    public String create(String name, @NonNull Set<GrantedAuthority> roles) {
        String[] rolesGet = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        return JWT.create().withClaim("name", name)
                .withArrayClaim("roles", rolesGet)
                .sign(algorithm);
    }

    public DecodedJWT compare(String token) throws JWTVerificationException {
        try {
            return JWT.require(algorithm).build().verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }
}
