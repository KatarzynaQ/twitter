package twitter.model;

import java.util.stream.Stream;

public class Dashboard {

    private TweetRepository repository;

    public Dashboard(TweetRepository tweetRepository) {
        this.repository = tweetRepository;
    }

    public Tweet create(String msg, String author) throws TweetRepositoryException {
        Tweet newTweet = new Tweet(msg);
        repository.add(newTweet, author);
        return newTweet;
    }

    public Stream<Tweet> load(Author author) throws TweetRepositoryException {
        return repository.allTweets(author);

    }

    public Stream<Tweet> loadAll() throws TweetRepositoryException {
        return repository.allTweets();
    }
}
