package twitter;

import java.util.Objects;

public class Tweet {
	private String message;
	private String author;

	public Tweet(String message) {
		this.message = message;
	}

	public Tweet(String message, String author) {
		this.author = author;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Tweet{" + "message='" + message + '\'' + ", author='" + author + '\'' + '}';
	}

	public String getMessage() {
		return message;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tweet)) return false;
		Tweet tweet = (Tweet) o;
		return Objects.equals(message, tweet.message) &&
				Objects.equals(author, tweet.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, author);
	}
}
