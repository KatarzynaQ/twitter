package twitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Objects;

@NamedQuery(name="Tweet.allTweets",query="select t FROM Tweet t")
@Entity
public class Tweet {
	@Id
	@GeneratedValue
	private int id;
	private String message;



	public Tweet(String message) {
		this.message = message;
	}




	@Override
	public String toString() {
		return "Tweet{" + "message='" + message + '\'' + ", author='" +  + '\'' + '}';
	}

	public String getMessage() {
		return message;
	}

	/*public String getAuthor() {
		return author;
	}*/

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tweet)) return false;
		Tweet tweet = (Tweet) o;
		return Objects.equals(message, tweet.message);
	}


}
