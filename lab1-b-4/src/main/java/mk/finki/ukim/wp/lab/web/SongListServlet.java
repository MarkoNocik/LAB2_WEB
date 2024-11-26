package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "song-list-servlet", urlPatterns = "/songList")
public class SongListServlet extends HttpServlet {

    private BookService bookService;
    private SpringTemplateEngine springTemplateEngine;

    public SongListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    public void init(){
        System.out.println("Servlet initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        HttpSession session = req.getSession();

        List<Song> songs = null;
        if(req.getParameter("searchInput") != null){
            songs = bookService.findByCategory((String) req.getParameter("searchInput"));
        }else{
            songs = bookService.listSongs();
        }

        if(songs.isEmpty()){
            songs = bookService.listSongs();
            context.setVariable("error", "Can not find song with the provided category");
        }else{
            context.removeVariable("error");
        }


        context.setVariable("songs", songs);


        springTemplateEngine.process("listSongs.html",context,resp.getWriter());
    }
}
