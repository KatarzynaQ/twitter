package twitter.web;

import twitter.model.Author;
import twitter.model.AuthorDoesntExistException;
import twitter.model.Dashboard;
import twitter.model.TweetRepositoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.stream.Collectors;

public class TwitterServlet extends HttpServlet {
    Dashboard dashboard;

    public TwitterServlet(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("tweets", dashboard.loadAll().collect(Collectors.toList()));
            req.getRequestDispatcher("twitter.jsp").forward(req, resp);
            //  resp.sendRedirect(".twitter");
        } catch (TweetRepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String message = req.getParameter("message");
            String author = currentUser(req);
            dashboard.create(message, author);
            resp.sendRedirect("/twitter");
        } catch (TweetRepositoryException e) {
            try {
                throw new AuthorDoesntExistException("this author doesnt exist", e);
            } catch (AuthorDoesntExistException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    private String currentUser(HttpServletRequest req) {
        return req.getSession().getAttribute(SessionKeys.CURRENT_USER).toString();
    }
}
