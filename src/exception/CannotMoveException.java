package exception;

public class CannotMoveException extends Exception {

	private static final long serialVersionUID = -1017407408368859619L;

	public CannotMoveException() {
		super();
	}
	
	public CannotMoveException(String message) {
		super(message);
	}
	
	public CannotMoveException(Throwable cause) {
		super(cause);
	}
	
	public CannotMoveException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
