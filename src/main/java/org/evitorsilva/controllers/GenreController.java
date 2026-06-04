package org.evitorsilva.controllers;

import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody GenreRequest request) {
        genreService.create(request);
        return ResponseEntity.ok('D');
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/genre/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @Valid @RequestBody GenreRequest request) {
        genreService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/genre/{id}")
    public ResponseEntity delete(
            @PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
