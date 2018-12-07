package exception;

public class ItemTypeNotFoundException extends Exception {

	private static final long serialVersionUID = 1081521220809880146L;

	public ItemTypeNotFoundException() {
		super();
	}
	
	public ItemTypeNotFoundException(String message) {
		super(message);
	}
	
	public ItemTypeNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ItemTypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
