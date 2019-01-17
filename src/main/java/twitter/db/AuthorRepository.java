package twitter.db;

import twitter.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.stream.Stream;

public class AuthorRepository {
    EntityManager entityManager;

    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Stream<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a").getResultStream();
    }

    public void populate() {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(new Author("admin", "admin"));
            entityManager.persist(new Author("admin1", "admin1"));
            entityManager.persist(new Author("admin2", "admin2"));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
