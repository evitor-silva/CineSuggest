package org.evitorsilva.services;

import org.evitorsilva.entities.GenresEntity;
import org.evitorsilva.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public List<GenresEntity> get(){
        return genreRepository.findAll();
    }
}
