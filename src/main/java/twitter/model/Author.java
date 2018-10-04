package twitter.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "t_ath")
    private Collection<Tweet> allWrittenTweets;


    public void saveTweets(Collection<Tweet>tweets){
    this.allWrittenTweets=tweets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Tweet> getAllWrittenTweets() {
        return allWrittenTweets;
    }

    public void setAllWrittenTweets(Collection<Tweet> allWrittenTweets) {
        this.allWrittenTweets = allWrittenTweets;
    }
}
