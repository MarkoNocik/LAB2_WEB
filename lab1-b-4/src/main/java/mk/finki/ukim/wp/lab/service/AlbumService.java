package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AlbumService {
    public List<Album> findAll();

    Album findById(Long albumId);
}