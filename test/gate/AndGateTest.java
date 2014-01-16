package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class AndGateTest extends TestCase {

	public void testAndGate() {
		try {
			new AndGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new AndGate(null, new Wire(), null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new AndGate(null, null, new Wire());
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
			new AndGate(new Wire(), null, new Wire());
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new AndGate(null, new Wire(), new Wire());
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
		
		try {
			new AndGate(new Wire(), new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in AndGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			AndGate andGate = new AndGate(new Wire(), new Wire(), new Wire());
			andGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in AndGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			AndGate andGate = new AndGate(new Wire(), new Wire(), new Wire());
			andGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in AndGate", false);
		}
	}
	
	public void testWireSignalChanged() throws WireException {
		try {
			Simulator sim = new Simulator();
			int time = sim.getTime();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire output = new Wire("output");
			new WireProbe(output);
			sim.addComponent(new AndGate(input1, input2, output));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			assertEquals(time + AndGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(true);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			assertEquals(time + AndGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(false);
			input2.setSignal(true);
			sim.run();
			assertEquals(false, output.getSignal());
			assertEquals(time + AndGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(true);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + AndGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
	}
}
