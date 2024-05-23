package ru.ccfit.golubevm.musicdbapp.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Album}
 */
@Value
public class CreateAlbumRequest implements Serializable {
    @NotNull
    @Size(max = 64)
    @NotBlank
    String title;
    LocalDate releasedOn;
    @Size(max = 32)
    String genre;
}