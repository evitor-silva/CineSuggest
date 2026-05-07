package org.evitorsilva.controllers;

import org.evitorsilva.util.DTO.requests.MediaRequest;
import org.evitorsilva.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MediaController {

    private final MovieService movieService;

    public MediaController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/media")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(movieService.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/media")
    public ResponseEntity create(@RequestBody MediaRequest request) {
        movieService.create(request);
        return ResponseEntity.ok(201);
    }
}
