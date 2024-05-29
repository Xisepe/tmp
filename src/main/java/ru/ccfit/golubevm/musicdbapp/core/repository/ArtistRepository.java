package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ccfit.golubevm.musicdbapp.core.repository.projection.ArtistIdWithLatestAlbumId;
import ru.ccfit.golubevm.musicdbapp.core.repository.projection.ArtistInfo;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer>, JpaSpecificationExecutor<Artist> {
    @Query(value = "SELECT a FROM Artist a JOIN a.albums alb GROUP BY a.id HAVING COUNT(alb) > :albumCount")
    List<Artist> findArtistsWithMoreAlbumsThan(@Param("albumCount") int albumCount);

    @Query(
            value = "select ar.id as id, ar.artist_name as artistName, count(al.id) as totalAlbums " +
                    "from artist ar " +
                    "left join album al on ar.id = al.artist_id " +
                    "group by ar.id",
            nativeQuery = true
    )
    List<ArtistInfo> getAllArtistsWithTotalAlbums();
    @Query(value = """
            select a.id artistId, b.id albumId
            from artist a
            left join lateral (
                select id
                from album
                where artist_id = a.id
                order by released_on desc
                limit 1
            ) b on true;
            """, nativeQuery = true)
    List<ArtistIdWithLatestAlbumId> getArtistsWithLatestAlbum();
}
