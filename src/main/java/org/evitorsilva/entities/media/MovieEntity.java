package org.evitorsilva.entities.media;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class MovieEntity extends MediaEntity {
    private int duration;
}
