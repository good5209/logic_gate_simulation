package wire;

import java.util.LinkedList;

/**
 * connected between gates, transmit and represent logic unit signal
 */
public class Wire {
	private String name;
	private boolean signal = false;
	private LinkedList<WireListener> listeners;
	
	/**
	 * default name wire
	 */
	public Wire() {
		this("Wire");
	}
	
	public Wire(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * set this wire signal
	 * @param signal
	 */
	public void setSignal(boolean signal) {
		if (this.signal != signal) {
			this.signal = signal;
			notifyListener();
		}
	}
	
	/**
	 * get this wire signal
	 * @return signal
	 */
	public boolean getSignal() {
		return signal;
	}
	
	/**
	 * add observe this wire listener
	 * @param listener
	 */
	public void listenSignal(WireListener listener) throws WireException {
		if (listener != null) {
			if (listeners == null) {
				listeners = new LinkedList<WireListener>();
			}
			listeners.add(listener);
			return;
		}
		throw new WireException("wire listener is null");
	}
	
	/**
	 * notify signal changed
	 */
	private void notifyListener() {
		if (listeners != null) {
			for (WireListener each : listeners) {
				each.wireSignalChanged();
			}
		}
	}
}
