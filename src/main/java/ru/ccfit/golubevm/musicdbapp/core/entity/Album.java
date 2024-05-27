package ru.ccfit.golubevm.musicdbapp.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "album", indexes = {
        @Index(name = "idx_album_title", columnList = "title"),
        @Index(name = "idx_album_released_on", columnList = "released_on"),
        @Index(name = "idx_album_genre", columnList = "genre")
})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @NotNull
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "released_on")
    private LocalDate releasedOn;

    @Size(max = 32)
    @Column(name = "genre", length = 32)
    private String genre;

    @Column(name = "total_songs", nullable = false)
    private Integer totalSongs;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "album_song",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_liked_album",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

}