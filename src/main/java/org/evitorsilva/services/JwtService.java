package org.evitorsilva.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JwtService {

    private final Algorithm algorithm = Algorithm.HMAC256("HS256");

    public String create(String name, Set<GrantedAuthority> roles) {
        String[] rolesGet = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        return JWT.create().withClaim("name", name).
                withArrayClaim("roles", rolesGet)
                .sign(algorithm);
    }

    public DecodedJWT compare(String token) throws Exception {
        try {
            return JWT.require(algorithm).build().verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }
}
