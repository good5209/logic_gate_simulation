package gate;

import simulate.SimulateComponent;
import simulate.Simulator;
import wire.WireListener;

/**
 * a kind of simulate component
 * which is attach wires and compute one or some input signal, produce one output signal
 */
public interface Gate extends SimulateComponent, WireListener {
	public void addOnSimulator(Simulator simulator) throws GateException;
}
