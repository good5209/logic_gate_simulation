package module;

import simulate.SimulateComponent;
import simulate.Simulator;
import gate.*;
import wire.Wire;

public class FullAdder implements SimulateComponent {
	private SimulateComponent halfAdder1, halfAdder2, orGate;
	
	public FullAdder(Wire input1, Wire input2, Wire carryInput, Wire sum, Wire carryOutput) throws GateException {
		if (input1 != null && input2 != null && carryInput != null && sum != null && carryOutput != null) {
			Wire mid1 = new Wire();
			Wire mid2 = new Wire();
			Wire mid3 = new Wire();
			halfAdder1 = new HalfAdder(input1, input2, mid2, mid1);
			halfAdder2 = new HalfAdder(mid2, carryInput, sum, mid3);
			orGate = new OrGate(mid1, mid3, carryOutput);
			return;
		}
		throw new GateException("FullAdder create failure");
	}

	@Override
	public void addOnSimulator(Simulator simulator) {
		halfAdder1.addOnSimulator(simulator);
		halfAdder2.addOnSimulator(simulator);
		orGate.addOnSimulator(simulator);
	}
}
