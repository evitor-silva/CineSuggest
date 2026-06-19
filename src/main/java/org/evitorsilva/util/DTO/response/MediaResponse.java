package org.evitorsilva.util.DTO.response;

import org.evitorsilva.util.Enums.EMedia;

import java.util.Set;

public record MediaResponse(String title, double Duration, EMedia type, int seasons, int episodes, Set<String> genres) {
}