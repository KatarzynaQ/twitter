package twitter.model;

import java.util.stream.Stream;

public class Dashboard {

    private TweetRepository repository;

    public Dashboard(TweetRepository tweetRepository) {

        this.repository = tweetRepository;
    }

    public Tweet create(String msg, String author) throws TweetRepositoryException {
        Tweet newTweet = new Tweet(msg, author);

        repository.add(newTweet);

        return newTweet;
    }

    public Stream<Tweet> load() throws TweetRepositoryException {
        return repository.allTweets();

    }

}
