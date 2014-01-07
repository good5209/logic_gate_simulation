package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class DummyGateTest extends TestCase {

	public void testDummyGate() {
		try {
			Simulator sim = new Simulator();
			
			Wire input = new Wire("input");
			Wire output = new Wire("output");
			new WireProbe(input);
			new WireProbe(output);
			sim.addComponent(new DummyGate(input, output));
			
			input.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			
			input.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		try {
			new DummyGate(new Wire(), null);
			assertFalse("An GateException should be thrown in DummyGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new DummyGate(null, new Wire());
			assertFalse("An GateException should be thrown in DummyGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new DummyGate(null, null);
			assertFalse("An GateException should be thrown in DummyGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
}
