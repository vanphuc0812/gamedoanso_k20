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
import utils.UrlUtils;

import java.io.IOException;


@WebServlet(urlPatterns = {UrlUtils.GAME, UrlUtils.NEW_GAME, UrlUtils.GAMEID})
public class GameServlet extends HttpServlet {
    GameService gameService = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player player = (Player) req.getSession().getAttribute("currentuser");
        Game game;
        String[] paths = req.getRequestURI().split("/");
        String gameID = paths.length > 3 ? paths[3] : "";
        System.out.println(gameID);
        game = gameService.loadGame(player.getUsername(), gameID);
        req.setAttribute("game", game);
        req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.NEW_GAME:
                String username = req.getParameter("username");
                Game newGame = gameService.createGame(username);
                req.setAttribute("game", newGame);
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            case UrlUtils.GAME:
                String gameID = req.getParameter("gameid");
                int guessNumber = Integer.parseInt(req.getParameter("guessNumber"));
                Game game = gameService.processGame(gameID, guessNumber);
                req.setAttribute("game", game);
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
        }
    }
}