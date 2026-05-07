package org.evitorsilva.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table( name= "genres")
public class GenresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
}
