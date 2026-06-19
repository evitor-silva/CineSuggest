package org.evitorsilva.controllers;

import jakarta.validation.constraints.NotNull;
import org.evitorsilva.entities.UserEntity;
import org.evitorsilva.util.DTO.requests.CreateUserRequest;
import org.evitorsilva.util.DTO.requests.LoginRequest;
import org.evitorsilva.services.JwtService;
import org.evitorsilva.services.UserService;
import org.evitorsilva.util.DTO.response.TokenUserLogged;
import org.evitorsilva.util.Interfaces.IUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
        return ResponseEntity.ok("200");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenUserLogged> loginUser(@RequestBody @NotNull LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        IUserDetails userLogged = (IUserDetails) authentication.getPrincipal();

        assert userLogged != null;
        UUID userId = userLogged.getId();

        Set<GrantedAuthority> roles = authentication.getAuthorities().stream()
                .filter(auth -> auth.getAuthority() != null && auth.getAuthority().startsWith("ROLE"))
                .collect(Collectors.toSet());

        String token = jwtService.create(authentication.getName(), userId, roles);

        return ResponseEntity.status(201).body(new TokenUserLogged(token, "Bearer"));
    }

}
