package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> ArtistfindById(Long id) {
        return artistRepository.findById(id);
    }
}