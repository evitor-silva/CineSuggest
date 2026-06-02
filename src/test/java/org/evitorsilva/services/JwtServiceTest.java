package org.evitorsilva.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService = new JwtService();

    @Test
    void create() throws Exception {
        String name = "user123";
        Set<GrantedAuthority> roles = Set.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );

        String token = jwtService.create(name, roles);
        DecodedJWT decodedJWT = jwtService.compare(token);
        assertNotNull(token);
        assertNotNull(decodedJWT);

        assertEquals(name, decodedJWT.getClaim("name").asString());
        String[] decodedRoles = decodedJWT.getClaim("roles").asArray(String.class);
        assertArrayEquals(
                roles.stream().map(role -> role.getAuthority()).toArray(String[]::new),
                decodedRoles
        );
    }

    @Test
    void compare() {
        String invalidToken = "invalid.token.value";
        assertThrows(Exception.class, () -> jwtService.compare(invalidToken));
    }
}