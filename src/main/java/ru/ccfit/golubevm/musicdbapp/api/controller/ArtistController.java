package ru.ccfit.golubevm.musicdbapp.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ccfit.golubevm.musicdbapp.api.dto.AlbumDto;
import ru.ccfit.golubevm.musicdbapp.api.dto.ArtistDto;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateAlbumRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.UpdateAlbumRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.UpdateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.CreateAlbumResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.CreateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UpdateAlbumResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UpdateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.core.service.AlbumService;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;
import ru.ccfit.golubevm.musicdbapp.core.service.mapper.AlbumMapper;
import ru.ccfit.golubevm.musicdbapp.core.service.mapper.ArtistMapper;

import java.util.List;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final ArtistMapper artistMapper;
    private final AlbumMapper albumMapper;

    @PostMapping("/")
    public List<CreateArtistResponse> createArtist(@RequestBody List<CreateArtistRequest> artists) {
        var models = artistMapper.toEntities(artists);
        return artistMapper.toCreateArtistResponses(artistService.createArtists(models));
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Integer id) {
        artistService.deleteArtist(id);
    }

    @GetMapping("/{id}")
    public ArtistDto getArtist(@PathVariable Integer id) {
        var artist = artistService.getArtist(id);
        return artistMapper.toArtistDto(artist);
    }

    @GetMapping("/")
    public List<ArtistDto> getAllArtist() {
        var artists = artistService.getAllArtist();
        return artistMapper.toArtistDtos(artists);
    }

    @PutMapping("/{id}")
    public UpdateArtistResponse updateArtist(@PathVariable Integer id, @RequestBody UpdateArtistRequest request) {
        var data = artistMapper.toEntity(request);
        var upd = artistService.updateArtist(id, data);
        return artistMapper.toUpdateArtistResponse(upd);
    }

    @PostMapping("/{id}/album")
    public CreateAlbumResponse createAlbum(@PathVariable Integer id, @RequestBody CreateAlbumRequest album) {
        var alb = albumMapper.toEntity(album);
        return albumMapper.toCreateAlbumResponse(albumService.createAlbum(id, alb));
    }

    @DeleteMapping("/{id}/album/{albumId}")
    public void deleteAlbum(@PathVariable("id") Integer id, @PathVariable("albumId") Integer albumId) {
        albumService.deleteAlbum(id, albumId);
    }

    @GetMapping("/{id}/album/{albumId}")
    public AlbumDto getAlbum(@PathVariable("id") Integer id, @PathVariable("albumId") Integer albumId) {
        var album = albumService.getAlbum(id, albumId);
        return albumMapper.toAlbumDto(album);
    }

    @PutMapping("/{id}/album/{albumId}")
    public UpdateAlbumResponse updateAlbum(@PathVariable("id") Integer id,
                                           @PathVariable("albumId") Integer albumId,
                                           @RequestBody UpdateAlbumRequest album) {
        var alb = albumMapper.toEntity(album);
        var upd = albumService.updateAlbum(id, albumId, alb);
        return albumMapper.toUpdateAlbumResponse(upd);
    }
}
