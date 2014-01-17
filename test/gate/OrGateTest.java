package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class OrGateTest extends TestCase {

	public void testOrGate() throws WireException {
		try {
			new OrGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new OrGate(null, new Wire(), null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new OrGate(null, null, new Wire());
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
			new OrGate(new Wire(), null, new Wire());
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new OrGate(null, new Wire(), new Wire());
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
		
		try {
			new OrGate(new Wire(), new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in OrGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			OrGate orGate = new OrGate(new Wire(), new Wire(), new Wire());
			orGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in OrGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			OrGate orGate = new OrGate(new Wire(), new Wire(), new Wire());
			orGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in OrGate", false);
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
			sim.addComponent(new OrGate(input1, input2, output));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, output.getSignal());
			assertEquals(time + OrGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(true);
			input2.setSignal(false);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + OrGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(false);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + OrGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
			
			input1.setSignal(true);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, output.getSignal());
			assertEquals(time + OrGate.DELAY_TIME, sim.getTime());
			time = sim.getTime();
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			OrGate gate = new OrGate(new Wire(), new Wire(), new Wire());
			gate.wireSignalChanged();
		} catch (GateException e) {
			assertTrue(false);
		}
	}
}
