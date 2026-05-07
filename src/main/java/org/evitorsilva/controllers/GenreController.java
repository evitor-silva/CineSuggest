package org.evitorsilva.controllers;

import org.evitorsilva.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(genreService.get());
    }
}
