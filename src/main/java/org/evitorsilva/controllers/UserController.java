package org.evitorsilva.controllers;

import org.evitorsilva.util.DTO.requests.CreateUserRequest;
import org.evitorsilva.util.DTO.requests.LoginRequest;
import org.evitorsilva.services.JwtService;
import org.evitorsilva.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
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
    public ResponseEntity loginUser(@RequestBody LoginRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            Set<GrantedAuthority> roles = authentication.getAuthorities().stream()
                    .filter(auth -> auth.getAuthority() != null && auth.getAuthority().startsWith("ROLE"))
                    .collect(Collectors.toSet());

            String token = jwtService.create(authentication.getName(), roles);

            return ResponseEntity.status(201).body(token);

        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
