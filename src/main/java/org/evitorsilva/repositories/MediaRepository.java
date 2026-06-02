package org.evitorsilva.repositories;

import org.evitorsilva.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

}
