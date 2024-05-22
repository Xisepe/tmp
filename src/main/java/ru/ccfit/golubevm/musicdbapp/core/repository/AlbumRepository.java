package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}