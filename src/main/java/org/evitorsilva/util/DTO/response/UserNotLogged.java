package org.evitorsilva.util.DTO.response;

import java.time.LocalDateTime;

public record UserNotLogged(LocalDateTime timestamp, int status, String error, String message, String path) {
}
