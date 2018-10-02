package twitter.executor;

public class SqlScriptExecutorException extends RuntimeException {
	public SqlScriptExecutorException(String message) {
		super(message);
	}

	public SqlScriptExecutorException(String message, Throwable cause) {
		super(message, cause);
	}
}
