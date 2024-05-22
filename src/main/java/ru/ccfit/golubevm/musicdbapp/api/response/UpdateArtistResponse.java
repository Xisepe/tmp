package ru.ccfit.golubevm.musicdbapp.api.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Artist}
 */
@Value
public class UpdateArtistResponse implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 128)
    @NotBlank
    String artistName;
    @Size(max = 255)
    String description;
    @Size(max = 32)
    String genre;
}