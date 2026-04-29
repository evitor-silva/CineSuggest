package org.evitorsilva.repositories;

import org.evitorsilva.entities.media.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MediaEntity, Long> {

}
