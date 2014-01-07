package gate;

@SuppressWarnings("serial")
public class GateException extends Exception {
	public GateException() {}
	
	/**
	 * gate exception
	 * @param message exception message
	 */
	public GateException(String message) {
		super(message);
	}
}
