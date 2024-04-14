package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;
import utils.UrlUtils;

import java.io.IOException;

@WebFilter(urlPatterns = UrlUtils.ALL)
public class UnLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Player player = (Player) req.getSession().getAttribute("currentuser");
        if (player == null && !isAuthUrl(req)) resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN);
        else
            filterChain.doFilter(req, resp);
    }

    private boolean isAuthUrl(HttpServletRequest req) {
        return req.getServletPath().equals(UrlUtils.LOGIN)
                || req.getServletPath().equals(UrlUtils.REGISTER);
    }
}
