package ru.ccfit.golubevm.musicdbapp.core.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ccfit.golubevm.musicdbapp.SongInfo;
import ru.ccfit.golubevm.musicdbapp.core.entity.Song;

import java.util.List;
import java.util.Objects;

public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findSongsByDurationBetweenOrderByTitle(@NotNull Integer duration, @NotNull Integer duration2);

    @Query(
            value = "select s.title, count(*) over(partition by ps.playlist_id) as total" +
                    " from song s join playlist_song ps on s.id = ps.song_id " +
                    "order by total " +
                    "limit 5",
            nativeQuery = true
    )
    List<SongInfo> findTopFiveMostPopularSongs();

}