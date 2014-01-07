package gate;

import simulate.Simulator;
import wire.Wire;

/**
 * obtain one input signal, and produce "or" signal to output wire
 */
public class NotGate implements Gate {
	public static int DELAY_TIME = 1;
	private Simulator simulator;
	private Wire input, output;
	
	/**
	 * Not gate
	 * @param input input wire
	 * @param output output wire
	 * @throws GateException attach wire is invalid
	 */
	public NotGate(Wire input, Wire output) throws GateException {
		if (input != null && output != null) {
			this.input = input;
			this.input.listenSignal(this);
			this.output = output;
			return;
		}
		throw new GateException("NotGate create failure");
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
				private boolean result = !input.getSignal(); // save now result
				@Override
				public void invokeAction() {
					output.setSignal(result);
				}
			});
		}
	}
}
