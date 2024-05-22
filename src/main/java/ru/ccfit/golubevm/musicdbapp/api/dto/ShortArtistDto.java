package ru.ccfit.golubevm.musicdbapp.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Artist}
 */
@Value
public class ShortArtistDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 128)
    String artistName;
    @Size(max = 255)
    String description;
    @Size(max = 32)
    String genre;
}