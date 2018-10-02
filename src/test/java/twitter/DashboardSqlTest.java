package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardSqlTest {

    private Dashboard dashboard;
    SqlTweetRepository sqlTweetRepository;

    @BeforeEach
    void beforeEach() {
        try {
            sqlTweetRepository=new SqlTweetRepository(DataSourceFactory.createDataSource());
        } catch (TweetRepositoryException e) {
            e.printStackTrace();
        }
        dashboard = new Dashboard(sqlTweetRepository);
    }

    @DisplayName("author should be able to create a new twit")
    @Test
    void twitAuthor() throws Exception{
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
    void db()throws Exception {
        // given
        String msg = "content";
        String author = "goobar";
        Tweet t1=new Tweet("goobar","hello world!");
Tweet t2=new Tweet("foobar","hello myWorld!");
Tweet t3=new Tweet("hoobar","hello");
        // when
        Stream<Tweet> allTweets = dashboard.load();

        // then
        assertThat(allTweets).containsExactly(t1,t2,t3);
    }
}


