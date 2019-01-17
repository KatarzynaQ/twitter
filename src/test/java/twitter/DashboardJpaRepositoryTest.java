package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Author;
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
        String author = "author";
        // when
        Tweet tweet = dashboard.create(msg, author);

        // then
        assertThat(tweet.getMessage()).isEqualTo(msg);

        String msg1 = "next message";
        Tweet tweet1 = dashboard.create(msg1, author);
        assertThat(tweet1.getMessage()).isEqualTo(msg1);
    }

    @DisplayName("should load two tweets created by author from the dashboard")
    @Test
    void db() throws Exception {
        // given
        Author author = new Author();
        author.setName("goobar");
        Tweet t1 = dashboard.create("t1", author.getName());
        Tweet t2 = dashboard.create("t2", author.getName());

        // when
        Stream<Tweet> allTweets = dashboard.load(author);

        // then
        assertThat(allTweets).containsExactlyInAnyOrder(t1, t2);
    }
}

