package ru.ccfit.golubevm.musicdbapp.core.repository.projection;

/**
 * Projection for {@link ru.ccfit.golubevm.musicdbapp.core.entity.User}
 */
public interface UserWithPlaylists {
    Integer getId();

    String getUsername();
}