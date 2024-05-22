package ru.ccfit.golubevm.musicdbapp.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ru.ccfit.golubevm.musicdbapp.core.entity.Song}
 */
@Value
public class ShortSongDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 128)
    String title;
    @NotNull
    Integer duration;
    @Size(max = 255)
    String songUrl;
}