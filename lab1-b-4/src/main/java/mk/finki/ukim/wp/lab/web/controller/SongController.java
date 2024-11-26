package mk.finki.ukim.wp.lab.web.controller;


import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final BookService bookService;
    private final AlbumService albumService;


    public SongController(BookService bookService, AlbumService albumService) {
        this.bookService = bookService;
        this.albumService = albumService;

    }
    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        if(error!=null){
            model.addAttribute("errorMessage", error);
        }
        model.addAttribute("songs",bookService.listSongs());

        return "listSongs";
    }
    @GetMapping("/add")
    public String addSongPage(Model model){
        model.addAttribute("song", new Song());
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }
    @PostMapping("/add")
    public String saveSong(@RequestParam String title, @RequestParam String trackId, @RequestParam String genre,@RequestParam Integer releaseYear, @RequestParam Long albumId){
    Album album = albumService.findById(albumId);
    bookService.saveSong(title, trackId, genre, releaseYear, album);

    return "redirect:/songs";
    }



    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        Song song = bookService.findById(songId);
        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }
        model.addAttribute("song", song);
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @PostMapping("/edit/{songId}")
    public String updateSong(
            @PathVariable Long songId,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId
    ) {
        Album album = albumService.findById(albumId);
        bookService.update(songId, title, trackId, genre, releaseYear, album);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/songs";
    }
}
