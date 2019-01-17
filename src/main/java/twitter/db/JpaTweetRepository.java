package twitter.db;

import twitter.model.Author;
import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JpaTweetRepository implements TweetRepository {
    EntityManager entityManager;

    public JpaTweetRepository(EntityManager entityManager) {
        //
        this.entityManager = entityManager;
    }

    @Override
    public void add(Tweet tweet, String authorName) throws TweetRepositoryException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Author author =
                    entityManager.createNamedQuery("Author.findByName", Author.class)
                            .setParameter("name", authorName).getSingleResult();
            author.saveTweet(tweet);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new TweetRepositoryException(e.getMessage(), e);
        }
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

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        List<Tweet> listOfAllTweets = new ArrayList<>();
        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a from Author a", Author.class);
        List<Author> listOfAuthors = authorQuery.getResultList();
        for (Author a : listOfAuthors) {
            listOfAllTweets.addAll(a.getAllWrittenTweets());
        }
        return listOfAllTweets.stream();
    }
}