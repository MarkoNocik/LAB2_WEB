package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "song-details-servlet", urlPatterns = "/trackDetails")
public class SongDetailsServlet extends HttpServlet {

    private final ArtistRepository artistRepository;
    private final BookRepository bookRepository;
    private final SpringTemplateEngine springTemplateEngine;

    @Autowired
    public SongDetailsServlet(ArtistRepository artistRepository, BookRepository bookRepository, SpringTemplateEngine springTemplateEngine) {
        this.artistRepository = artistRepository;
        this.bookRepository = bookRepository;
        this.springTemplateEngine = springTemplateEngine;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        HttpSession session = req.getSession();
        String trackId = (String) session.getAttribute("trackId");
        Long artistId = Long.valueOf(req.getParameter("artistId"));

        Song song = bookRepository.findByTrackId(trackId);
        Artist artist = artistRepository.findById(artistId).orElse(null);
        if(song == null || artist == null){
            return;
        }
        Artist existingArtist = song.getPerformers().stream().filter(a -> a.getId().equals(artistId)).findFirst().orElse(null);

        if(existingArtist == null){
            Artist temp = bookRepository.addArtistToSong(artist,song);
        }

        context.setVariable("song", song);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
