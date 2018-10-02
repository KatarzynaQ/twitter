package twitter.executor;

import org.apache.commons.io.IOUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClasspathSqlScriptExecutor implements SqlScriptExecutor {
	private DataSource dataSource;

	public ClasspathSqlScriptExecutor(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void execute(String classpath) throws SqlScriptExecutorException {
		try (Connection connection = dataSource.getConnection()) {
			InputStream sqlScriptInputStream = getClass().getResourceAsStream("/" + classpath);
			String sql = IOUtils.toString(sqlScriptInputStream, "UTF-8");
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (IOException | SQLException e) {
			throw new SqlScriptExecutorException(String.format("Failed to execute script %s", classpath),
				e);
		}
	}
}
