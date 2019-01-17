package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Author;
import twitter.model.Tweet;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class AuthorTest {
    Author author;


    @BeforeEach
    void beforeEach() {
        author = new Author();
    }

    @DisplayName("Author should save given tweet with adding it into their list")
    @Test
    void test() throws Exception {
        Tweet tweet0 = new Tweet("tweet0");
        Tweet tweet1 = new Tweet("tweet1");
        author.saveTweet(tweet0);

        assertThat(author.getAllWrittenTweets()).containsExactlyInAnyOrder(tweet0);
        author.saveTweet(tweet1);
        assertThat(author.getAllWrittenTweets()).containsExactlyInAnyOrder(tweet0, tweet1);
    }
}
