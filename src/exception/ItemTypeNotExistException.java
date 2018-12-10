package exception;

public class ItemTypeNotExistException extends Exception {

	private static final long serialVersionUID = 1081521220809880146L;

	public ItemTypeNotExistException() {
		super();
	}
	
	public ItemTypeNotExistException(String message) {
		super(message);
	}
	
	public ItemTypeNotExistException(Throwable cause) {
		super(cause);
	}
	
	public ItemTypeNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
