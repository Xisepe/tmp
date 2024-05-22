package ru.ccfit.golubevm.musicdbapp.core.service;

import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> createArtists(List<Artist> artist);
    Artist updateArtist(Integer id, Artist artist);
    Artist getArtist(Integer id);
    List<Artist> getAllArtist();
    void deleteArtist(Integer id);
}
