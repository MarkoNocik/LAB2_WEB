package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // GET Request to Show Artist List for a Song
    @GetMapping
    public String showArtists(@RequestParam String trackId, HttpSession session, Model model) {
        // Save the trackId in the session
        session.setAttribute("trackId", trackId);

        // Add data to the model
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("trackId", trackId);

        // Render the view
        return "listArtists";
    }

    // POST Request to Add an Artist to the Song
    @PostMapping
    public String addArtistToSong(@RequestParam Long artistId, HttpSession session) {
        // Retrieve trackId from the session
        String trackId = (String) session.getAttribute("trackId");

        // Logic to associate the artist with the song (implement in service/repository)
        // Example:
        // songRepository.addArtistToSong(trackId, artistId);

        // Redirect to the songs page or another relevant view
        return "redirect:/songs";
    }
}
