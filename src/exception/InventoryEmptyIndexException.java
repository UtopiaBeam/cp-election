package exception;

public class InventoryEmptyIndexException extends Exception {

	private static final long serialVersionUID = -8084865983246523863L;
	
	public InventoryEmptyIndexException() {
		super();
	}
	
	public InventoryEmptyIndexException(String message) {
		super(message);
	}
	
	public InventoryEmptyIndexException(Throwable cause) {
		super(cause);
	}
	
	public InventoryEmptyIndexException(String message, Throwable cause) {
		super(message, cause);
	}

}
