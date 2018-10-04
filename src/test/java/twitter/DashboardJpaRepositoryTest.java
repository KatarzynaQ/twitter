package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Dashboard;
import twitter.db.JpaTweetRepository;
import twitter.model.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardJpaRepositoryTest {

    Dashboard dashboard;
    JpaTweetRepository repository;

    @BeforeEach
    void beforeEach() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = factory.createEntityManager();
        repository = new JpaTweetRepository(entityManager);
        dashboard = new Dashboard(repository);

    }

    @DisplayName("author should create new tweet ")
    @Test
    void twitAuthor() throws Exception {
        // given
        String msg = "content";
        int authorId =1;

        // when
        Tweet tweet = dashboard.create(msg,authorId);

        // then

        assertThat(tweet.getMessage()).isEqualTo(msg);
    }

    @DisplayName("should load created tweet from the dashboard")
    @Test
    void db() throws Exception {
        // given
        String msg = "content";
        String author = "goobar";
        Tweet expectedTweet1 = new Tweet("hello world!");
        Tweet expectedTweet2 = new Tweet("hello myWorld!");
        Tweet expectedTweet3 = new Tweet("hello");
        dashboard.create("hello world!", anyAuthor());
        dashboard.create("hello myWorld!", anyAuthor());
        dashboard.create("hello", anyAuthor());

        // when
        Stream<Tweet> allTweets = dashboard.load();

        // then
        assertThat(allTweets).containsExactlyInAnyOrder(expectedTweet1, expectedTweet2, expectedTweet3);
    }

    private int anyAuthor() {
        return 3;
    }
}

