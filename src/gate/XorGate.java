package gate;

import java.util.LinkedList;

import simulate.SimulateComponent;
import simulate.Simulator;
import wire.Wire;

public class XorGate implements Gate {
	private LinkedList<SimulateComponent> components;
	
	public XorGate(Wire input1, Wire input2, Wire output) throws GateException {
		components = new LinkedList<SimulateComponent>();
		Wire mid1 = new Wire();
		Wire mid2 = new Wire();
		Wire mid3 = new Wire();
		Wire mid4 = new Wire();
		components.add(new NotGate(input1, mid1));
		components.add(new NotGate(input2, mid2));
		components.add(new AndGate(mid1, input2, mid3));
		components.add(new AndGate(mid2, input1, mid4));
		components.add(new OrGate(mid3, mid4, output));
	}

	@Override
	public void addOnSimulator(Simulator simulator) {
		for (SimulateComponent each : components) {
			each.addOnSimulator(simulator);
		}
	}

	@Override
	public void wireSignalChanged() {
		// do nothing
	}
}
