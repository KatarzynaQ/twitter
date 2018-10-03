package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Dashboard;
import twitter.model.JpaTweetRepository;
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

    @DisplayName("should create new tweet ")
    @Test
    void twitAuthor() throws Exception {
        // given
        String msg = "content";
        String author = "goobar";

        // when
        Tweet tweet = dashboard.create(msg, author);

        // then
        assertThat(tweet.getAuthor()).isEqualTo(author);
        assertThat(tweet.getMessage()).isEqualTo(msg);
    }

    @DisplayName("should load created tweet from the dashboard")
    @Test
    void db() throws Exception {
        // given
        String msg = "content";
        String author = "goobar";
        Tweet expectedTweet1 = new Tweet("hello world!", "goobar");
        Tweet expectedTweet2 = new Tweet("hello myWorld!", "foobar");
        Tweet expectedTweet3 = new Tweet("hello", "hoobar");
        dashboard.create("hello world!", "goobar");
        dashboard.create("hello myWorld!", "foobar");
        dashboard.create("hello", "hoobar");

        // when
        Stream<Tweet> allTweets = dashboard.load();

        // then
        assertThat(allTweets).containsExactlyInAnyOrder(expectedTweet1, expectedTweet2, expectedTweet3);
    }
}

