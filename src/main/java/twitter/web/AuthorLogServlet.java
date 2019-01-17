package twitter.web;

import twitter.model.Dashboard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorLogServlet extends HttpServlet {
    private EntityManager entityManager;

    public AuthorLogServlet(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("log.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        String pass = req.getParameter("password");
        LoginManager loginManager = new LoginManager(entityManager);
        if (loginManager.isValid(name, pass)) {
            req.getSession().setAttribute(SessionKeys.CURRENT_USER, name);
        }
        resp.sendRedirect("/twitter");

    }
}
