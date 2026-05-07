package org.evitorsilva.repositories;

import org.evitorsilva.entities.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenresEntity, Long> {
}
