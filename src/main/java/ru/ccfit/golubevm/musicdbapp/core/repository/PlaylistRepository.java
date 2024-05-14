package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ccfit.golubevm.musicdbapp.core.entity.Playlist;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}