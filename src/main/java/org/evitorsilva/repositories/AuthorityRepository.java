package org.evitorsilva.repositories;

import org.evitorsilva.util.Enums.ERoles;
import org.evitorsilva.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    AuthorityEntity findByAuthority(ERoles role);
}
