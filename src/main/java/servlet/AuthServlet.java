package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;
import service.AuthService;
import utils.JspUtils;
import utils.UrlUtils;

import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtils.LOGIN, UrlUtils.REGISTER, UrlUtils.LOGOUT})
public class AuthServlet extends HttpServlet {
    AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.LOGIN:
                req.getRequestDispatcher(JspUtils.LOGIN).forward(req, resp);
                break;
            case UrlUtils.LOGOUT:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN);
                break;
            case UrlUtils.REGISTER:
                req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UrlUtils.LOGIN.equals(req.getServletPath())) {
//            req.getSession().invalidate();
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            Player player = authService.login(username, password);
            if (player != null) {
                req.getSession().setAttribute("currentuser", player);
                resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
            } else {
                req.setAttribute("errors", "Username or password is incorrect");
                req.getRequestDispatcher(JspUtils.LOGIN).forward(req, resp);
            }
        } else if (UrlUtils.REGISTER.equals(req.getServletPath())) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("passwordConfirm");
            if (!password.equals(confirmPassword)) {
                req.setAttribute("errors", "Confirm password is not matched");
                req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
            }
            String error = authService.register(username, password);
            if (error == null)
                resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN);
            else {
                req.setAttribute("errors", error);
                req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
            }
        }
    }
}
