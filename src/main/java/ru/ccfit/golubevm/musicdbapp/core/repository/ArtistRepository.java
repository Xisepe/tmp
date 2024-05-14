package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ccfit.golubevm.musicdbapp.ArtistInfo;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer>, JpaSpecificationExecutor<Artist> {
    @Query(value = "SELECT a FROM Artist a JOIN a.albums alb GROUP BY a.id HAVING COUNT(alb) > :albumCount")
    List<Artist> findArtistsWithMoreAlbumsThan(@Param("albumCount") int albumCount);

    @Query(
            value = "select ar.id as id, ar.artist_name as artist_name, count(al.id) as total_albums " +
                    "from artist ar " +
                    "left join album al on ar.id = al.artist_id " +
                    "group by ar.id",
            nativeQuery = true
    )
    List<ArtistInfo> getAllArtistsWithTotalAlbums();
}