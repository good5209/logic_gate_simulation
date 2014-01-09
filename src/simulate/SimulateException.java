package simulate;

@SuppressWarnings("serial")
public class SimulateException extends Exception {
	public SimulateException() {}
	
	/**
	 * simulator exception
	 * @param message exception message
	 */
	public SimulateException(String message) {
		super(message);
	}
}
