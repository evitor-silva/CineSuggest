package org.evitorsilva.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.evitorsilva.entities.MediaEntity;
import org.evitorsilva.util.DTO.requests.MediaRequest;
import org.evitorsilva.services.MediaService;
import org.evitorsilva.util.DTO.response.MediaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
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

    @GetMapping("/media/{title}")
    public ResponseEntity<?> getTitle(@PathVariable String title) throws Exception{
        try {
            Optional<MediaResponse> mediaResponse = mediaService.get(title);

            System.out.print(mediaResponse);

            if(mediaResponse.isEmpty()) return ResponseEntity.status(404).body("Não achou");

            return ResponseEntity.ok(mediaResponse.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/media")
    public ResponseEntity create(@RequestBody MediaRequest request) {
        mediaService.create(request);
        return ResponseEntity.status(201).build();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @PutMapping("/media/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @RequestBody MediaRequest request) {
        mediaService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearerAuth") })
    @DeleteMapping("/media/{id}")
    public ResponseEntity delete(
            @PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}