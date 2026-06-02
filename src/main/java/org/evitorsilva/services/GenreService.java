package org.evitorsilva.services;

import org.evitorsilva.entities.GenresEntity;
import org.evitorsilva.repositories.GenreRepository;
import org.evitorsilva.util.DTO.requests.GenreRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenresEntity> get() {
        return genreRepository.findAll();
    }

    public void create(GenreRequest request) {
        GenresEntity genresEntity = new GenresEntity();
        genresEntity.setTitle(request.title());
        genreRepository.save(genresEntity);
    }

    public void update(Long id, GenreRequest request) {
        GenresEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setTitle(request.title());
        genreRepository.save(genre);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
