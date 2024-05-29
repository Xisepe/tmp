package ru.ccfit.golubevm.musicdbapp.core.repository.projection;

/**
 * Projection for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Song}
 */
public interface SongInfo {
    String getTitle();
    Long getTotal();
}