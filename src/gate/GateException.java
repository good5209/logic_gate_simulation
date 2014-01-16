package gate;

import simulate.SimulateComponentException;

@SuppressWarnings("serial")
public class GateException extends SimulateComponentException {
	public GateException() {}
	
	/**
	 * gate exception
	 * @param message exception message
	 */
	public GateException(String message) {
		super(message);
	}
}
