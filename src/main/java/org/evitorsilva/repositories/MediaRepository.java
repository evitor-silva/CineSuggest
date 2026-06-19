package org.evitorsilva.repositories;

import org.evitorsilva.entities.MediaEntity;
import org.evitorsilva.util.DTO.response.MediaResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    Optional<MediaEntity> findByTitle(String title);
}
