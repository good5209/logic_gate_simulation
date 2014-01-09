package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class NotGateTest extends TestCase {

	public void testNotGate() {
		try {
			Simulator sim = new Simulator();
			
			Wire input = new Wire("input");
			Wire output = new Wire("output");
			new WireProbe(input);
			new WireProbe(output);
			sim.addComponent(new NotGate(input, output));
			
			input.setSignal(false);
			sim.run();
			assertTrue(output.getSignal());
			
			input.setSignal(true);
			sim.runUntil(2);
			assertFalse(output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			new NotGate(new Wire(), null);
			assertFalse("An GateException should be thrown in NotGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NotGate(null, new Wire());
			assertFalse("An GateException should be thrown in NotGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NotGate(null, null);
			assertFalse("An GateException should be thrown in NotGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
	
	public void testLoopNotGate() {
		try {
			Simulator sim = new Simulator();
			
			Wire wire = new Wire("wire");
			new WireProbe(wire);
			sim.addComponent(new NotGate(wire, wire));
			
			wire.setSignal(false);
			sim.runUntil(1);
			sim.runUntil(0);
			sim.runUntil(1);
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
	}
}
