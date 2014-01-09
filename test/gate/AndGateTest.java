package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class AndGateTest extends TestCase {

	public void testAndGate() {
		try {
			Simulator sim = new Simulator();
			final Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire output = new Wire("output");
			new WireProbe(output);
			sim.addComponent(new AndGate(input1, input2, output));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(true);
			sim.run();
			assertEquals(false, output.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			new AndGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new AndGate(new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new AndGate(null, null, null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
}
