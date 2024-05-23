package ru.ccfit.golubevm.musicdbapp.core.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.ccfit.golubevm.musicdbapp.api.dto.AlbumDto;
import ru.ccfit.golubevm.musicdbapp.api.dto.ShortAlbumDto;
import ru.ccfit.golubevm.musicdbapp.api.dto.ShortArtistDto;
import ru.ccfit.golubevm.musicdbapp.api.dto.ShortSongDto;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateAlbumRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.UpdateAlbumRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.CreateAlbumResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UpdateAlbumResponse;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;
import ru.ccfit.golubevm.musicdbapp.core.entity.Song;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AlbumMapper {
    @Mapping(target = "artistId", source = "artist.id")
    ShortAlbumDto toShortAlbumDto(Album album);
    @Mapping(target = "totalSongs", expression = "java(0)")
    Album toEntity(CreateAlbumRequest request);

    CreateAlbumResponse toCreateAlbumResponse(Album album);

    AlbumDto toAlbumDto(Album album);

    ShortArtistDto toShortArtistDto(Artist artist);

    ShortSongDto toShortSongDto(Song song);

    Album toEntity(UpdateAlbumRequest request);
    UpdateAlbumResponse toUpdateAlbumResponse(Album album);
}
