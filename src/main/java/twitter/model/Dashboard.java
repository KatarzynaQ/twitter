package twitter.model;

import java.util.stream.Stream;

public class Dashboard {

    private TweetRepository repository;

    public Dashboard(TweetRepository tweetRepository) {
        this.repository = tweetRepository;
    }

    public Tweet create(String msg, int authorId) throws TweetRepositoryException {
        Tweet newTweet = new Tweet(msg);
        repository.add(newTweet, authorId);
        return newTweet;
    }

    public Stream<Tweet> load(Author author) throws TweetRepositoryException {
        return repository.allTweets(author);

    }

}
