package twitter.web;

import twitter.model.DashboardFactory;
import twitter.model.MyEntityManagerFactory;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContexInitializer implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        registerTwitterServlet(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void registerTwitterServlet(ServletContext servletContext) {
        servletContext.addServlet("TwitterServlet", new TwitterServlet(DashboardFactory.createDashboard()))
                .addMapping("/twitter");
        servletContext.addServlet("AuthorLogServlet", new AuthorLogServlet(MyEntityManagerFactory.getInstance().createEntityManager()))
                .addMapping("/log");
    }


///jedna fabryka która moze tworzyć wiele Entity Managerów

}
