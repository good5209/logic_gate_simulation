package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class OrGateTest extends TestCase {

	public void testOrGate() {
		try {
			Simulator sim = new Simulator();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire output = new Wire("output");
			new WireProbe(output);
			sim.addComponent(new OrGate(input1, input2, output));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(false);
			sim.run();
			assertEquals(true, output.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		try {
			new OrGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new OrGate(new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new OrGate(null, null, null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
}
