package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Category;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private List<Song> songs = new ArrayList<>();

    public BookRepository() {
        for(int i=1; i<6; i++){
            songs.add(new Song("TrackId: " + i, "Title: " + i, "Genre: " + i, i + 2000, new Category("Category" + i), new Album((long)i, "Name: " + i, "Genre: " + i, "Year: " + (i+2000))));
        }
    }

    public List<Song> findAll(){
        return  songs;
    }

    public Song findByTrackId(String trackId){
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Song findById(Long id){
        return songs.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        Song foundSong = findByTrackId(song.getTrackId());

        if(foundSong == null){
            return null;
        }
        foundSong.getPerformers().add(artist);
        return artist;
    }

    public List<Song> findByCategory(String category){
        return songs.stream().filter(s -> s.getCategory().getCategory().contains(category)).collect(Collectors.toList());
    }

    public void saveSong(String title, String trackId, String genre, Integer releaseYear, Album album) {
        songs.add(new Song(trackId, title, genre, releaseYear, new Category("Category"), album));
    }

    public void update(Long songId, String title, String trackId, String genre, int releaseYear, Album album) {
        Song song = findById(songId);
        if(song!= null){
            song.setTitle(title);
            song.setTrackId(trackId);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            song.setAlbum(album);
        }
    }

    public void delete(Long id) {
        this.songs = songs.stream().filter(s -> !s.getId().equals(id)).collect(Collectors.toList());
    }
}
