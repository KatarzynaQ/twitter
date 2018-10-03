package twitter.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

public class DBCleaner {
	public static void clean(String tableName, DataSource dataSource)throws Exception{
		try(Connection connection=dataSource.getConnection(); Statement statement=
			connection.createStatement()){
			statement.execute("DROP TABLE IF EXISTS "+tableName);
		}
	}
}
