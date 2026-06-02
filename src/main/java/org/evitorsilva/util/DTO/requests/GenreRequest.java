package org.evitorsilva.util.DTO.requests;
import jakarta.validation.constraints.NotBlank;

public record GenreRequest(@NotBlank String title) {
}
