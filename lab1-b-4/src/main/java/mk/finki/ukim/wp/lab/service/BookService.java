package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Song findById(Long id);
    List<Song> findByCategory(String category);
    void saveSong(String title, String trackId, String genre, Integer releaseYear, Album album);

    void update(Long songId, String title, String trackId, String genre, int releaseYear, Album album);

    void delete(Long id);

}
