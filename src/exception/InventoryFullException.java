package exception;

public class InventoryFullException extends Exception {

	private static final long serialVersionUID = -164985566829121492L;

	public InventoryFullException() {
		super();
	}
	
	public InventoryFullException(String message) {
		super(message);
	}
	
	public InventoryFullException(Throwable cause) {
		super(cause);
	}
	
	public InventoryFullException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
