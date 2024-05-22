package ru.ccfit.golubevm.musicdbapp.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Album}
 */
@Value
public class ShortAlbumDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 64)
    String title;
    Integer artistId;
    LocalDate releasedOn;
    @Size(max = 32)
    String genre;
    @NotNull
    Integer totalSongs;
    Set<Integer> songIds;
}