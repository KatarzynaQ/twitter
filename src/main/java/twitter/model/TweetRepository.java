package twitter.model;

import java.util.stream.Stream;

public interface TweetRepository {
    /**
     * Saves a tweet inside the database.
     *
     * @param tweet    a tweet to save
     * @param AuthorId
     * @throws TweetRepositoryException if fails to add a tweet
     */
    public void add(Tweet tweet, String AuthorId) throws TweetRepositoryException;

    public Stream<Tweet> allTweets(Author author) throws TweetRepositoryException;

    public Stream<Tweet> allTweets() throws TweetRepositoryException;
}
