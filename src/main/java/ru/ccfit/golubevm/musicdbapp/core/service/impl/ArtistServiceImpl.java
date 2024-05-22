package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;
import ru.ccfit.golubevm.musicdbapp.core.repository.ArtistRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    @Override
    public List<Artist> createArtists(List<Artist> artist) {
        return artistRepository.saveAll(artist);
    }

    @Override
    public Artist updateArtist(Integer id, Artist artist) {
        var a = getArtist(id);
        a.setArtistName(artist.getArtistName());
        a.setGenre(artist.getGenre());
        a.setDescription(artist.getDescription());
        return artistRepository.save(a);
    }

    @Override
    public Artist getArtist(Integer id) {
        //TODO: exception
        return artistRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public void deleteArtist(Integer id) {
        artistRepository.deleteById(id);
    }
}
