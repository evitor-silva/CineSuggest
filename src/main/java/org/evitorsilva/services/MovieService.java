package org.evitorsilva.services;

import org.evitorsilva.entities.MediaEntity;
import org.evitorsilva.util.DTO.requests.MediaRequest;
import org.evitorsilva.repositories.MovieRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void create(MediaRequest request) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setDuration(request.duration());
        mediaEntity.setTitle(request.title());
        mediaEntity.setEpisodes(request.episodes());
        mediaEntity.setType(request.type());
        mediaEntity.setSeasons(request.seasons());

        movieRepository.save(mediaEntity);
    }

    public List<MediaEntity> get(){
        return movieRepository.findAll();
    }
}
