package module;

import simulate.SimulateComponentException;
import simulate.Simulator;
import simulate.SimulateComponent;
import gate.*;
import wire.Wire;

public class HalfAdder implements SimulateComponent {
	private SimulateComponent xorGate, andGate;
	
	public HalfAdder(Wire input1, Wire input2, Wire sum, Wire carry) throws GateException {
		if (input1 != null && input2 != null && sum != null && carry != null) {
			xorGate = new XorGate(input1, input2, sum);
			andGate = new AndGate(input1, input2, carry);
			return;
		}
		throw new GateException("HalfAdder create failure");
	}

	@Override
	public void addOnSimulator(Simulator simulator) throws SimulateComponentException {
		xorGate.addOnSimulator(simulator);
		andGate.addOnSimulator(simulator);
	}
}
