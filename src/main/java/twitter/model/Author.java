package twitter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NamedQuery(name = "Author.findByName", query = "SELECT a from Author a where a.name = :name")
@Entity
public class Author {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "t_ath")
    private Collection<Tweet> allWrittenTweets;
    private String name;
    private String password;

    public Author() {
        this.allWrittenTweets = new ArrayList<>();
    }

    public Author(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void saveTweet(Tweet tweet) {
        this.allWrittenTweets.add(tweet);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
