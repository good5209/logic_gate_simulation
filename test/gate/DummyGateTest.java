package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class DummyGateTest extends TestCase {

	public void testDummyGate() throws WireException {
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
		
		try {
			new DummyGate(new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in DummyGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			DummyGate dummyGate = new DummyGate(new Wire(), new Wire());
			dummyGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in DummyGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			DummyGate dummyGate = new DummyGate(new Wire(), new Wire());
			dummyGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in DummyGate", false);
		}
	}
	
	public void testWireSignalChanged() throws WireException {
		try {
			Simulator sim = new Simulator();
			int time = sim.getTime();
			Wire input = new Wire("input");
			Wire output = new Wire("output");
			new WireProbe(input);
			new WireProbe(output);
			sim.addComponent(new DummyGate(input, output));
			
			input.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			assertEquals(time + DummyGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + DummyGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
	}
}
