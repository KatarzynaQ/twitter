package twitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class InMemoryTweetRepository implements TweetRepository {
    Collection<Tweet>tweets;

    public InMemoryTweetRepository() {
        this.tweets = new ArrayList<>();
    }

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {
        tweets.add(tweet);
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        return tweets.stream();
    }
}
