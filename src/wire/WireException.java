package wire;

@SuppressWarnings("serial")
public class WireException extends Exception {
	public WireException() {}
	
	/**
	 * wire exception
	 * @param message exception message
	 */
	public WireException(String message) {
		super(message);
	}
}
