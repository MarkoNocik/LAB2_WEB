package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    private List<Artist> artists = new ArrayList<Artist>();

    public ArtistRepository() {
        for(int i = 1; i < 6; i++){
            artists.add(new Artist((long) i, "Name" + i, "Lastname" + i, "Bio" + i));
        }
    }

    public List<Artist> findAll(){
        return  artists;
    }

    public Optional<Artist> findById(Long id){
        return artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }
}
