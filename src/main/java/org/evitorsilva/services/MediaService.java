package org.evitorsilva.services;

import org.evitorsilva.entities.GenresEntity;
import org.evitorsilva.entities.MediaEntity;
import org.evitorsilva.repositories.MediaRepository;
import org.evitorsilva.util.DTO.requests.MediaRequest;
import org.evitorsilva.util.DTO.response.MediaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void create(MediaRequest request) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setDuration(request.duration());
        mediaEntity.setTitle(request.title());
        mediaEntity.setEpisodes(request.episodes());
        mediaEntity.setType(request.type());
        mediaEntity.setSeasons(request.seasons());

        mediaRepository.save(mediaEntity);
    }

    public Page<MediaEntity> get(int page, int size) {
        return mediaRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    /**
     * @param title
     * @return Optional
     */
    public Optional<MediaResponse> get(String title) {
        Optional<MediaEntity> mediaOptional = mediaRepository.findByTitle(title);

        if (mediaOptional.isPresent()) {
            System.out.println("SUCESSO: Encontrou a mídia no banco! -> " + mediaOptional.get().getTitle());
        } else {
            System.out.println("AVISO: O banco de dados retornou VAZIO para o título: [" + title + "]");
        }
        return mediaRepository.findByTitle(title)
                .map(media -> new MediaResponse(
                        media.getTitle(),
                        media.getDuration(),
                        media.getType(),
                        media.getSeasons(),
                        media.getEpisodes(),
                        media.getGenres().stream()
                                .map(GenresEntity::getTitle)
                                .collect(Collectors.toSet())
                ));
    }

    public void update(Long id, MediaRequest request) {
        MediaEntity mediaEntity = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));
        mediaEntity.setDuration(request.duration());
        mediaEntity.setTitle(request.title());
        mediaEntity.setEpisodes(request.episodes());
        mediaEntity.setType(request.type());
        mediaEntity.setSeasons(request.seasons());
        mediaRepository.save(mediaEntity);
    }

    public void delete(Long id) {
        mediaRepository.deleteById(id);
    }
}