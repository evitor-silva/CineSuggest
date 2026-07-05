package org.evitorsilva.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.evitorsilva.services.GenreService;
import org.evitorsilva.util.DTO.requests.GenreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(genreService.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/genre")
    public ResponseEntity create(@RequestBody GenreRequest request) {
        genreService.create(request);
        return ResponseEntity.ok('D');
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @PutMapping("/genre/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody GenreRequest request) {
        genreService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @DeleteMapping("/genre/{id}")
    public ResponseEntity delete(
            @PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
