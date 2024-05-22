package ru.ccfit.golubevm.musicdbapp.core.service;

import ru.ccfit.golubevm.musicdbapp.core.entity.Album;

public interface AlbumService {
    Album createAlbum(Integer artistId, Album album);
    void deleteAlbum(Integer artistId, Integer albumId);

}
