package ru.ccfit.golubevm.musicdbapp.core.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.ccfit.golubevm.musicdbapp.api.dto.ShortAlbumDto;
import ru.ccfit.golubevm.musicdbapp.core.entity.Album;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AlbumMapper {
    ShortAlbumDto toShortAlbumDto(Album album);
}
