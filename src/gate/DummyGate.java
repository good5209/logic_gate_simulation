package gate;

import simulate.Simulator;
import simulate.SimulateAction;
import simulate.SimulateException;
import wire.Wire;
import wire.WireException;

/**
 * simply redirect input signal to output wire
 */
public class DummyGate implements Gate {
	public static int DELAY_TIME = 0;
	private Simulator simulator;
	private Wire input, output;
	
	/**
	 * Dummy gate
	 * @param input input wire
	 * @param output output wire
	 * @throws GateException attach wire is invalid
	 */
	public DummyGate(Wire input, Wire output) throws GateException {
		if (input != null && output != null) {
			this.input = input;
			try {
				input.listenSignal(this);
			} catch (WireException e) {
				e.printStackTrace();
			}
			this.output = output;
			return;
		}
		throw new GateException("DummyGate create failure");
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
				simulator.addAction(DELAY_TIME, new SimulateAction() {
					private boolean result = input.getSignal(); // save now result
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
