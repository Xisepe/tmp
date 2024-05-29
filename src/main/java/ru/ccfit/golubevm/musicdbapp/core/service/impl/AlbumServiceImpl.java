package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;
import ru.ccfit.golubevm.musicdbapp.core.repository.AlbumRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.AlbumService;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final ArtistService artistService;
    private final AlbumRepository albumRepository;

    @Override
    @Transactional
    public Album createAlbum(Integer artistId, Album album) {
        var artist = artistService.getArtist(artistId);
        artist.incTotalAlbums();
        artistService.save(artist);
        album.setArtist(artist);
        return albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(Integer artistId, Integer albumId) {
        artistService.checkArtist(artistId);
        albumRepository.deleteById(albumId);
    }

    @Override
    public Album getAlbum(Integer artistId, Integer albumId) {
        artistService.checkArtist(artistId);
        return albumRepository.findById(albumId).orElseThrow();
    }

    @Override
    public Album updateAlbum(Integer artistId, Integer albumId, Album album) {
        var alb = getAlbum(artistId, albumId);
        alb.setGenre(album.getGenre());
        alb.setTitle(album.getTitle());
        alb.setReleasedOn(album.getReleasedOn());
        return albumRepository.save(alb);
    }

    @Override
    public void checkAlbum(Integer albumId) {
        if (!albumRepository.existsById(albumId)) throw new NoSuchElementException();
    }
}
