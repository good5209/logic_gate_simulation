package gate;

import junit.framework.TestCase;

import simulate.*;
import wire.*;

public class XorGateTest extends TestCase {

	public void testXorGate() throws WireException {
		try {
			new XorGate(new Wire(), null, null);
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(null, new Wire(), null);
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(null, null, new Wire());
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(new Wire(), null, new Wire());
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(null, new Wire(), new Wire());
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(null, null, null);
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new XorGate(new Wire(), new Wire(), new Wire());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in XorGate", false);
		}
	}
	
	public void testAddOnSimulator() {
		try {
			XorGate xorGate = new XorGate(new Wire(), new Wire(), new Wire());
			xorGate.addOnSimulator(null);
			assertFalse("An GateException should be thrown in XorGate", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			XorGate xorGate = new XorGate(new Wire(), new Wire(), new Wire());
			xorGate.addOnSimulator(new Simulator());
			assertTrue(true);
		} catch (GateException e) {
			assertTrue("No GateException should be thrown in XorGate", false);
		}
	}
	
	public void testWireSignalChanged() throws WireException {
		try {
			Simulator sim = new Simulator();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire output = new Wire("output");
			new WireProbe(output);
			sim.addComponent(new XorGate(input1, input2, output));
			
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
			assertEquals(false, output.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			XorGate gate = new XorGate(new Wire(), new Wire(), new Wire());
			gate.wireSignalChanged();
		} catch (GateException e) {
			assertTrue(false);
		}
	}
}
