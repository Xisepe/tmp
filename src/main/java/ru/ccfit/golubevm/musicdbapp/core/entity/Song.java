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
@Table(name = "song", indexes = {
        @Index(name = "idx_song_title", columnList = "title"),
        @Index(name = "idx_song_under_90_sec", columnList = "title")
})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @NotNull
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Size(max = 255)
    @Column(name = "song_url")
    private String songUrl;

    @ManyToMany(mappedBy = "songs")
    private Set<Album> albums = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "songs")
    private Set<Playlist> playlists = new LinkedHashSet<>();

}