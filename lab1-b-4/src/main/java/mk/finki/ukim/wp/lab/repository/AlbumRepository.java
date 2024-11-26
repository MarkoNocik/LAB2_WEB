package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository {
    List<Album> albums = new ArrayList<Album>();

    public AlbumRepository() {
       for(int i=1; i<=5; i++){
           albums.add(new Album((long)i, "Album: " + i, "Genre: " + i, "Year: " + (i+2000)));
       }
    }

    public List<Album> findAll(){
        return  albums;
    }

    public Album findById(Long albumId) {
        return albums.stream().filter(a -> a.getId().equals(albumId)).findFirst().orElse(null);
    }
}
