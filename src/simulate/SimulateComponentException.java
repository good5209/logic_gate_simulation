package simulate;

@SuppressWarnings("serial")
public class SimulateComponentException extends Exception {
	public SimulateComponentException() {}
	
	/**
	 * gate exception
	 * @param message exception message
	 */
	public SimulateComponentException(String message) {
		super(message);
	}
}
