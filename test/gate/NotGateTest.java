package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class NotGateTest extends TestCase {

	public void testNotGate() throws WireException {
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
		
		try {
			new NotGate(new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in NotGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			NotGate notGate = new NotGate(new Wire(), new Wire());
			notGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in NotGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			NotGate notGate = new NotGate(new Wire(), new Wire());
			notGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in NotGate", false);
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
			sim.addComponent(new NotGate(input, output));
			
			input.setSignal(false);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + NotGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input.setSignal(true);
			sim.runUntil(2);
			assertEquals(false, output.getSignal());
			assertEquals(time + NotGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
	}
	
	public void testLoopNotGate() throws WireException {
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
		
		try {
			NotGate gate = new NotGate(new Wire(), new Wire());
			gate.wireSignalChanged();
		} catch (GateException e) {
			assertTrue(false);
		}
	}
}
