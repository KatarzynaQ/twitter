package twitter.db.executor;

import org.apache.commons.io.IOUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlScriptExecutorImp implements SqlScriptExecutor {

	private DataSource dataSource;

	public SqlScriptExecutorImp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void execute(String classPath) throws SqlScriptExecutorException {
		try (Connection connection=dataSource.getConnection(); Statement statement=connection.createStatement()) {

			String script = IOUtils.toString(getClass().getResourceAsStream("/" + classPath), "UTF-8");
			statement.execute(script);
		}
		catch (SQLException | IOException e) {
			throw new SqlScriptExecutorException(String.format("Failed to execute script %s using " +
				"SqlScriptExecutor", classPath), e);
		}
	}
}

