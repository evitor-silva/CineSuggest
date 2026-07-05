package org.evitorsilva.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.evitorsilva.util.Enums.EMedia;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "MEDIAS")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "medias_genres",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("medias")
    private Set<GenresEntity> genres = new HashSet<>();
}
