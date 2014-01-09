package gate;

import simulate.SimulateException;
import simulate.Simulator;
import wire.Wire;

public class OrGate implements Gate {
	public static int DELAY_TIME = 3;
	private Simulator simulator;
	private Wire input1, input2, output;
	
	/**
	 * Or gate
	 * @param input1 input wire 1
	 * @param input2 input wire 2
	 * @param output output wire
	 * @throws GateException attach wire is invalid
	 */
	public OrGate(Wire input1, Wire input2, Wire output) throws GateException {
		if (input1 != null && input2 != null && output != null) {
			this.input1 = input1;
			this.input2 = input2;
			this.input1.listenSignal(this);
			this.input2.listenSignal(this);
			this.output = output;
			return;
		}
		throw new GateException("OrGate create failure");
	}
	
	@Override
	public void addOnSimulator(Simulator simulator) {
		this.simulator = simulator;
		wireSignalChanged(); // add initial work
	}
	
	@Override
	public void wireSignalChanged() {
		if (simulator != null) {
			try {
				simulator.addGateAction(DELAY_TIME, new GateAction() {
					private boolean result = input1.getSignal() || input2.getSignal(); // save now result
					@Override
					public void invokeAction() {
						output.setSignal(result);
					}
				});
			} catch (SimulateException e) {
				e.printStackTrace();
			}
		}
	}
}
