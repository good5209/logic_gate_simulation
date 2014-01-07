package gate;

import simulate.Simulator;
import wire.Wire;

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
			input.listenSignal(this);
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
			simulator.addGateAction(DELAY_TIME, new GateAction() {
				private boolean result = input.getSignal(); // save now result
				@Override
				public void invokeAction() {
					output.setSignal(result);
				}
			});
		}
	}
}
