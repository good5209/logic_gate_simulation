package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class NorGateTest extends TestCase {

	public void testNorGate() throws WireException {
		try {
			new NorGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(null, new Wire(), null);
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(null, null, new Wire());
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(new Wire(), null, new Wire());
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(null, new Wire(), new Wire());
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(null, null, null);
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new NorGate(new Wire(), new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in NorGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			NorGate norGate = new NorGate(new Wire(), new Wire(), new Wire());
			norGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in NorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			NorGate norGate = new NorGate(new Wire(), new Wire(), new Wire());
			norGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in NorGate", false);
		}
	}
	
	public void testWireSignalChanged() throws WireException {
		try {
			Simulator sim = new Simulator();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire output = new Wire("output");
			new WireProbe(output);
			sim.addComponent(new NorGate(input1, input2, output));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(true, output.getSignal());
			
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
			assertEquals(false, output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			NorGate gate = new NorGate(new Wire(), new Wire(), new Wire());
			gate.wireSignalChanged();
		} catch (GateException e) {
			assertTrue(false);
		}
	}
}
