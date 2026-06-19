package org.evitorsilva.util.Interfaces;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IUserDetails extends UserDetails {
   @NotNull UUID getId();
}
