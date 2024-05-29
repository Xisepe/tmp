package ru.ccfit.golubevm.musicdbapp.core.repository.projection;

/**
 * Projection for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Artist}
 */
public interface ArtistInfo {
    Integer getId();

    String getArtistName();
    Long getTotalAlbums();
}