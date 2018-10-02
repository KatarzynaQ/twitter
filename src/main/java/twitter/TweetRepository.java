package twitter;

import java.util.stream.Stream;

public interface TweetRepository {
    /**
     * Saves a tweet inside the database.
     *
     * @param tweet a tweet to save
     * @throws TweetRepositoryException if fails to add a tweet

     */
    public void add(Tweet tweet)throws TweetRepositoryException;
    public Stream<Tweet>allTweets()throws TweetRepositoryException;
}
