package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}