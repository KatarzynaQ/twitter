package twitter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Author;
import twitter.model.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthorAndTweetIntegrationTest {

    EntityManagerFactory factory;

    @BeforeEach
    void beforeEach() {
        factory = Persistence.createEntityManagerFactory("test");
    }

    @AfterEach
    void afterEach() {
        factory.close();
    }

    @DisplayName("should save author with his/her tweets into database")
    @Test
    void test() throws Exception {
        EntityManager entityManager = factory.createEntityManager();

        Tweet tweet0 = new Tweet("im too tired to understand hibernate");
        Tweet tweet1 = new Tweet("but i'm still writing");

        Author author = new Author();
        author.saveTweet(tweet0);
        author.saveTweet(tweet1);


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(author);
        transaction.commit();

        //checking if author saved in database has his tweets
        Author foundAuthor = entityManager.createQuery("select a from Author " + "a", Author.class).getSingleResult();
        assertThat(foundAuthor.getAllWrittenTweets()).extracting("message").containsExactlyInAnyOrder(tweet0.getMessage(), tweet1.getMessage());


    }

}
