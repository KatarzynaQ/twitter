package twitter.model;

import javax.persistence.*;
import java.util.stream.Stream;

public class JpaTweetRepository implements TweetRepository {
    EntityManager entityManager;

    public JpaTweetRepository(EntityManager entityManager) {
        //
        this.entityManager = entityManager;
    }

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(tweet);
        transaction.commit();
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        return entityManager.createNamedQuery("Tweet.allTweets", Tweet.class).getResultStream();
    }
}
