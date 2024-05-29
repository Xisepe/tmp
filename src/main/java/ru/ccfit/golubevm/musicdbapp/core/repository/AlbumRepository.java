package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Procedure(value = "calculate_total_album_duration")
    Integer getTotalDuration(@Param("id") Integer id);
}