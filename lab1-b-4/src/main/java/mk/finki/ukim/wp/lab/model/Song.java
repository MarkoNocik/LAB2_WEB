package mk.finki.ukim.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Song {
    private static long nextId = 1;
    private Long id;
    private Album album;

    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    List<Artist> performers = new ArrayList<>();

    private Category category;

    public Song(String trackId, String title, String genre, int releaseYear, Category category, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.category = category;
        this.album = album;

        this.id = nextId++;
    }
}
