package twitter.model;

public class TweetRepositoryException extends Exception {
    public TweetRepositoryException(String message) {
        super(message);
    }

    public TweetRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
