package exception;

public class CannotAttackException extends Exception {

	private static final long serialVersionUID = 4944218055463410899L;
	
	public CannotAttackException() {
		super();
	}
	
	public CannotAttackException(String message) {
		super(message);
	}
	
	public CannotAttackException(Throwable cause) {
		super(cause);
	}
	
	public CannotAttackException(String message, Throwable cause) {
		super(message, cause);
	}

}
