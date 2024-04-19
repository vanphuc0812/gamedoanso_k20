package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Game;
import model.Player;
import service.GameService;
import utils.JspUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    GameService gameService = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> allGame = gameService.loadAllGameByUsername(((Player) req.getSession().getAttribute("currentuser")).getUsername());
        req.setAttribute("allGame", allGame);
        req.getRequestDispatcher(JspUtils.HOME).forward(req, resp);
    }
}
