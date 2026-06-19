package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CATALOG_MEDIA")
public class CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(
            name = "CATALOG_MEDIA_CONTENT",
            joinColumns = @JoinColumn(name = "CATALOG_ENTITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTENT_ID")
    )
    private Set<MediaEntity> content;
}