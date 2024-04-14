package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JspUtils;
import utils.UrlUtils;

import java.io.IOException;

@WebServlet(urlPatterns = UrlUtils.INTERNAL_ERROR)
public class CommonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspUtils.INTERNAL_ERROR).forward(req, resp);
    }
}
