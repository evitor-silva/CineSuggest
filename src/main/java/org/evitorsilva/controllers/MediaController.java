package org.evitorsilva.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.evitorsilva.entities.MediaEntity;
import org.evitorsilva.util.DTO.requests.MediaRequest;
import org.evitorsilva.services.MediaService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/media")
    public ResponseEntity<PagedModel<MediaEntity>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MediaEntity> mediaPage = mediaService.get(page, size);
        return ResponseEntity.ok(new PagedModel<>(mediaPage));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/media")
    public ResponseEntity create(@Valid  @RequestBody MediaRequest request) {
        mediaService.create(request);
        return ResponseEntity.status(201).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/media/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @Valid @RequestBody MediaRequest request) {
        mediaService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/media/{id}")
    public ResponseEntity delete(
            @PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}