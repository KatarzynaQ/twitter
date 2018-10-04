package twitter.db;

import twitter.model.Author;
import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import javax.persistence.*;
import java.util.stream.Stream;

public class JpaTweetRepository implements TweetRepository {
    EntityManager entityManager;

    public JpaTweetRepository(EntityManager entityManager) {
        //
        this.entityManager = entityManager;
    }

    @Override
    public void add(Tweet tweet, int authorId) throws TweetRepositoryException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author=new Author();
        try {
            Author singleResult =
                    entityManager.createNamedQuery("Author.findById", Author.class)
                            .setParameter("id", authorId).getSingleResult();
            author.saveTweet(tweet);
            entityManager.merge(author);
        }
        catch(NoResultException e){
            author.saveTweet(tweet);
            entityManager.persist(author);

        }
        transaction.commit();
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        return entityManager.createNamedQuery("Tweet.allTweets", Tweet.class).getResultStream();
    }
}
