package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Dashboard;
import twitter.model.InMemoryTweetRepository;
import twitter.model.Tweet;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardInMemoryTest {

	private Dashboard dashboard;
	InMemoryTweetRepository inMemoryTweetRepository;

	@BeforeEach
	void beforeEach() {
	inMemoryTweetRepository=new InMemoryTweetRepository();
		dashboard = new Dashboard(inMemoryTweetRepository);

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
	void db() throws Exception{
		// given
		String msg = "content";
		String author = "goobar";
		Tweet tweet = dashboard.create(msg, author);

		// when
		Stream<Tweet> allTweets = dashboard.load();

		// then
		assertThat(allTweets).containsOnly(tweet);
	}
}
