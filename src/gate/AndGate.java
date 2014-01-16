package gate;

import simulate.Simulator;
import simulate.SimulateAction;
import simulate.SimulateException;
import wire.Wire;
import wire.WireException;

public class AndGate implements Gate {
	public static int DELAY_TIME = 3;
	private Simulator simulator;
	private Wire input1, input2, output;
	
	/**
	 * And gate
	 * @param input1 input wire 1
	 * @param input2 input wire 2
	 * @param output output wire
	 * @throws GateException attach wire is invalid
	 */
	public AndGate(Wire input1, Wire input2, Wire output) throws GateException {
		if (input1 != null && input2 != null && output != null) {
			this.input1 = input1;
			this.input2 = input2;
			try {
				this.input1.listenSignal(this);
				this.input2.listenSignal(this);
			} catch (WireException e) {
				e.printStackTrace();
			}
			this.output = output;
			return;
		}
		throw new GateException("AndGate create failure");
	}
	
	@Override
	public void addOnSimulator(Simulator simulator) throws GateException {
		if (simulator != null) {
			this.simulator = simulator;
			wireSignalChanged(); // add initial work
			return;
		}
		throw new GateException("simulator is null");
	}
	
	@Override
	public void wireSignalChanged() {
		if (simulator != null) {
			try {
				simulator.addAction(DELAY_TIME, new SimulateAction() {
					private boolean result = input1.getSignal() && input2.getSignal(); // save now result
					@Override
					public void invoke() {
						output.setSignal(result);
					}
				});
			} catch (SimulateException e) {
				e.printStackTrace();
			}
		}
	}
}
