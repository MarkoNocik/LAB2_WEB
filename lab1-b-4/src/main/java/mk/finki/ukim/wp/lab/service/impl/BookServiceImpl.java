package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository songRepository;

    public BookServiceImpl( BookRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song findById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public List<Song> findByCategory(String category) {
        return this.songRepository.findByCategory(category);
    }

    @Override
    public void saveSong(String title, String trackId, String genre, Integer releaseYear, Album album) {
        this.songRepository.saveSong(title, trackId, genre, releaseYear, album);
    }

    @Override
    public void update(Long songId, String title, String trackId, String genre, int releaseYear, Album album) {
        this.songRepository.update(songId, title, trackId, genre, releaseYear, album);
    }

    @Override
    public void delete(Long id) {
        this.songRepository.delete(id);
    }
}
