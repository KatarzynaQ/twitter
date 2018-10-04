package twitter.db;


import twitter.db.DBCleaner;
import twitter.db.executor.SqlScriptExecutor;
import twitter.db.executor.SqlScriptExecutorImp;
import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class SqlTweetRepository implements TweetRepository {
    DataSource dataSource;

    public SqlTweetRepository(DataSource dataSource) throws TweetRepositoryException {
        this.dataSource = dataSource;
        try {
            DBCleaner.clean("tweet",dataSource);
        } catch (Exception e) {
            wrapEx("cannot create sql tweet repository");
        }
    }

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {
        SqlScriptExecutor executor=new SqlScriptExecutorImp(dataSource) ;
        executor.execute("tweet.sql");
        try(Connection connection=dataSource.getConnection();
            PreparedStatement insertStatement=connection.prepareStatement("INSERT into tweet(author, message) values(?,?)")){


        //    insertStatement.setString(1,tweet.getAuthor());
            insertStatement.setString(2,tweet.getMessage());
          insertStatement.execute();

        } catch (SQLException e) {
            wrapEx("Fail to add new tweet into sql database");
        }


    }

    private void wrapEx(String msg)throws TweetRepositoryException {
        throw new TweetRepositoryException (msg);
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        SqlScriptExecutor executor=new SqlScriptExecutorImp(dataSource) ;
        executor.execute("tweet.sql");
        try(Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from tweet ");
            Collection<Tweet>allTweets=new ArrayList<>();
            while (resultSet.next()){
                allTweets.add(new Tweet(resultSet.getString("message")));
            }

            return allTweets.stream();
        } catch (SQLException e) {
            wrapEx("Fail to get all tweets");
            return null;
        }


    }
}
