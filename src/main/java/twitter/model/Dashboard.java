package twitter.model;

import java.util.stream.Stream;

public class Dashboard {

    private TweetRepository repository;

    public Dashboard(TweetRepository tweetRepository) {
        this.repository = tweetRepository;
    }

    public Tweet create(String msg, int authorId) throws TweetRepositoryException {
        Author author=new Author();
        author.setId(authorId);
        Tweet newTweet = new Tweet(msg);
        author.saveTweet(newTweet);


        repository.add(newTweet, authorId);

        return newTweet;
    }

    public Stream<Tweet> load() throws TweetRepositoryException {
        return repository.allTweets();

    }

}
