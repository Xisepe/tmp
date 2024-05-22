package ru.ccfit.golubevm.musicdbapp.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "artist", indexes = {
        @Index(name = "idx_artist_artist_name", columnList = "artist_name"),
        @Index(name = "idx_artist_genre", columnList = "genre")
})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @NotNull
    @Column(name = "artist_name", nullable = false, length = 128)
    private String artistName;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 32)
    @Column(name = "genre", length = 32)
    private String genre;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private Set<Album> albums = new LinkedHashSet<>();

}