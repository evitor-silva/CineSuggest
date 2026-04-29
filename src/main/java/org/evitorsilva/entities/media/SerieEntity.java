package org.evitorsilva.entities.media;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "serie")
public class SerieEntity extends MediaEntity {
    private int episodes;
    private int seasons;
}
