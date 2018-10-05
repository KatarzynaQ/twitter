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
        try {
            Author author =
                    entityManager.createNamedQuery("Author.findById", Author.class)
                            .setParameter("id", authorId).getSingleResult();
            author.saveTweet(tweet);
        } catch (NoResultException e) {
            Author newAuthor = new Author();
            newAuthor.saveTweet(tweet);
            entityManager.persist(newAuthor);

        }
        transaction.commit();
    }

    @Override
    public Stream<Tweet> allTweets(Author author) throws TweetRepositoryException {

        Author foundAuthor = entityManager.find(Author.class, author.getId());
        if (foundAuthor != null) {
            return foundAuthor.getAllWrittenTweets().stream();
        } else {
            return Stream.empty();
        }

    }
}