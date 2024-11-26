package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "artist-servlet", urlPatterns = "/artist")
public class АrtistServlet extends HttpServlet {

    private ArtistRepository artistRepository;

    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    public АrtistServlet(ArtistRepository artistRepository, SpringTemplateEngine springTemplateEngine) {
        this.artistRepository = artistRepository;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);



        HttpSession session = req.getSession();
        session.setAttribute("trackId", req.getParameter("trackId"));
        context.setVariable("artists", artistRepository.findAll());
        context.setVariable("trackId", req.getParameter("trackId"));

        springTemplateEngine.process("listArtists.html", context, resp.getWriter());
    }
}
