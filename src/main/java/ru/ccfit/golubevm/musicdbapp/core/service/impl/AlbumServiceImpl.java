package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;
import ru.ccfit.golubevm.musicdbapp.core.repository.AlbumRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.AlbumService;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final ArtistService artistService;
    private final AlbumRepository albumRepository;
    @Override
    public Album createAlbum(Integer artistId, Album album) {
        var artist = artistService.getArtist(artistId);
        album.setArtist(artist);
        return albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(Integer artistId, Integer albumId) {
        albumRepository.deleteById(albumId);
    }
}
