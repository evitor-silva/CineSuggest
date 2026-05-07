package org.evitorsilva.util.DTO.requests;

import org.evitorsilva.util.Enums.EMedia;

public record MediaRequest(EMedia type, String title, double duration, int episodes, int seasons) {
}
