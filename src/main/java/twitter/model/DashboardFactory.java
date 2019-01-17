package twitter.model;

import twitter.db.JpaTweetRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DashboardFactory {
    private static JpaTweetRepository repository;

    public static Dashboard createDashboard() {
        EntityManager entityManager = MyEntityManagerFactory.getInstance().createEntityManager();
        repository = new JpaTweetRepository(entityManager);
        Dashboard dashboard = new Dashboard(repository);
        return dashboard;
    }
}

