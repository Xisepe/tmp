package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ccfit.golubevm.musicdbapp.core.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}