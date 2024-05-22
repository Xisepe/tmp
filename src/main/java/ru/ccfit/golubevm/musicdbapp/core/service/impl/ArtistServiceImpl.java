package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.entity.Artist;
import ru.ccfit.golubevm.musicdbapp.core.repository.ArtistRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.ArtistService;

import java.util.List;
import java.util.NoSuchElementException;

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
        if (!artistRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        artist.setId(id);
        return artistRepository.save(artist);
    }

    @Override
    public Artist getArtist(Integer id) {
        //TODO: exception
        return artistRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteArtist(Integer id) {
        artistRepository.deleteById(id);
    }
}
