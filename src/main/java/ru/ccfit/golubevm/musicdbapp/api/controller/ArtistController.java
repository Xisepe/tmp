package ru.ccfit.golubevm.musicdbapp.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ccfit.golubevm.musicdbapp.api.dto.ArtistDto;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.UpdateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.CreateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UpdateArtistResponse;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;
import ru.ccfit.golubevm.musicdbapp.core.service.mapper.ArtistMapper;

import java.util.List;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    private final ArtistMapper artistMapper;

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

    @PutMapping("/{id}")
    public UpdateArtistResponse updateArtist(@PathVariable Integer id, @RequestBody UpdateArtistRequest request) {
        var data = artistMapper.toEntity(request);
        var upd = artistService.updateArtist(id, data);
        return artistMapper.toUpdateArtistResponse(upd);
    }
}
