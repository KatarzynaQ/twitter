package twitter.web;

import twitter.db.AuthorRepository;
import twitter.db.DataSourceFactory;
import twitter.model.Dashboard;
import twitter.model.DashboardFactory;
import twitter.model.MyEntityManagerFactory;
import twitter.model.TweetRepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class DatabasePopulator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        AuthorRepository authorRepository = new AuthorRepository(MyEntityManagerFactory.getInstance().createEntityManager());
        authorRepository.populate();
    }
}
