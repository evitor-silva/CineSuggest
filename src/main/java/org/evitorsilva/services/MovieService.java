package org.evitorsilva.services;

import org.evitorsilva.util.DTO.requests.MovieRequest;
import org.evitorsilva.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ResponseEntity create(MovieRequest request) {
        //movieRepository.save(movieEntity);
        return null;
    }
}
