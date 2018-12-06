package exception;

public class CannotUseItemException extends Exception {

	private static final long serialVersionUID = 330095865982706300L;

	public CannotUseItemException() {
		super();
	}
	
	public CannotUseItemException(String message) {
		super(message);
	}
	
	public CannotUseItemException(Throwable cause) {
		super(cause);
	}
	
	public CannotUseItemException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
