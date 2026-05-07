package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.evitorsilva.util.Enums.EMedia;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "medias")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private EMedia type;

    private double duration;

    private int episodes;
    private int seasons;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenresEntity> genres = new HashSet<>();
}
