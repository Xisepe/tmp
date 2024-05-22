package ru.ccfit.golubevm.musicdbapp.core.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.ccfit.golubevm.musicdbapp.api.dto.ArtistDto;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.UpdateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.CreateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UpdateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AlbumMapper.class}
)
public interface ArtistMapper {
    Artist toEntity(CreateArtistRequest request);

    Artist toEntity(UpdateArtistRequest request);

    List<Artist> toEntities(List<CreateArtistRequest> artists);

    CreateArtistResponse toCreateArtistResponse(Artist artist);

    UpdateArtistResponse toUpdateArtistResponse(Artist artist);

    List<CreateArtistResponse> toCreateArtistResponses(List<Artist> artists);

    ArtistDto toArtistDto(Artist artist);
    List<ArtistDto> toArtistDtos(List<Artist> artists);
}
