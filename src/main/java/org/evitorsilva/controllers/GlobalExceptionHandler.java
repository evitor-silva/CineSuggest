package org.evitorsilva.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.evitorsilva.exceptions.UserNotLoggedException;
import org.evitorsilva.util.DTO.response.UserNotLogged;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<UserNotLogged> handleJwtError(JWTVerificationException ex, HttpServletRequest request) {
        return buildResponse("Token expirado", request);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<UserNotLogged> handleInsufficientAuthentication(InsufficientAuthenticationException ex, HttpServletRequest request) {
        return buildResponse("Token inválido ou expirado", request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<UserNotLogged> handleErrorAuthentication(AuthenticationException ex, HttpServletRequest request) {
        return buildResponse("Login ou Senha Incorretos", request);
    }

    @ExceptionHandler(UserNotLoggedException.class)
    public ResponseEntity<UserNotLogged> handleUserNotLogged(UserNotLoggedException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), request);
    }

    private ResponseEntity<UserNotLogged> buildResponse(String message, HttpServletRequest request) {
        UserNotLogged error = new UserNotLogged(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
