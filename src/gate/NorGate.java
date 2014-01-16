package gate;

import simulate.Simulator;
import wire.Wire;

public class NorGate implements Gate {
	private Gate orGate, notGate;
	
	public NorGate(Wire input1, Wire input2, Wire output) throws GateException {
		Wire mid = new Wire();
		orGate = new OrGate(input1, input2, mid);
		notGate = new NotGate(mid, output);
	}
	
	@Override
	public void addOnSimulator(Simulator simulator) throws GateException {
		orGate.addOnSimulator(simulator);
		notGate.addOnSimulator(simulator);
	}
	
	@Override
	public void wireSignalChanged() {
		// do nothing
	}
}
