package module;

import java.util.LinkedList;

import simulate.Simulator;
import simulate.SimulateComponent;
import gate.GateException;
import wire.Wire;

public class RippleCarryAdder implements SimulateComponent {
	private LinkedList<SimulateComponent> adders;
	
	public RippleCarryAdder(int num, Wire[] a, Wire[] b, Wire[] sum, Wire carry) throws GateException {
		if (num > 0
				&& a != null && b != null && sum != null && carry != null
				&& (a.length == num) && (b.length == num) && (sum.length == num)) {
			adders = new LinkedList<SimulateComponent>();
			
			Wire[] carryWires = new Wire[num + 1];
			for (int i = 0; i < num; i++) {
				carryWires[i] = new Wire();
			}
			carryWires[0].setSignal(false); // first carry is earth wire
			carryWires[num] = carry; // last carry is output carry
			for (int i = 0; i < num; i++) {
				adders.add(new FullAdder(a[i], b[i], carryWires[i], sum[i], carryWires[i + 1]));
			}
			return;
		}
		throw new GateException("RippleCarryAdder create failure");
	}
	
	@Override
	public void addOnSimulator(Simulator simulator) {
		for (SimulateComponent each : adders) {
			each.addOnSimulator(simulator);
		}
	}
}
