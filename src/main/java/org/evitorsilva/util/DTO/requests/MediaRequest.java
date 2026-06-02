package org.evitorsilva.util.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import org.evitorsilva.util.Enums.EMedia;

public record MediaRequest(@NotBlank EMedia type, String title, double duration, int episodes, int seasons) {
}
