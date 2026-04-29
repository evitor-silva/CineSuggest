package org.evitorsilva.entities;

import jakarta.persistence.*;
import org.evitorsilva.entities.media.MediaEntity;

import java.util.Set;

@Entity
@Table(name = "catalog_media")
public class CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany
    private Set<MediaEntity> content;
}

