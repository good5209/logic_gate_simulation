package module;

import junit.framework.TestCase;

import simulate.*;
import gate.GateException;
import wire.*;

public class FullAdderTest extends TestCase {

	public void testFullAdder() {
		try {
			Simulator sim = new Simulator();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire carryIn = new Wire("carry in");
			Wire sum = new Wire("sum");
			Wire carryOut = new Wire("carry out");
			new WireProbe(sum);
			new WireProbe(carryOut);
			sim.addComponent(new FullAdder(input1, input2, carryIn, sum, carryOut));
			
			input1.setSignal(false);
			input2.setSignal(false);
			carryIn.setSignal(false);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(false, carryOut.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(false);
			carryIn.setSignal(false);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(false, carryOut.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(true);
			carryIn.setSignal(false);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(false, carryOut.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(true);
			carryIn.setSignal(false);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(true, carryOut.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(false);
			carryIn.setSignal(true);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(false, carryOut.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(false);
			carryIn.setSignal(true);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(true, carryOut.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(true);
			carryIn.setSignal(true);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(true, carryOut.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(true);
			carryIn.setSignal(true);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(true, carryOut.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			new FullAdder(new Wire(), null, null, null, null);
			assertFalse("An GateException should be thrown in FullAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new FullAdder(new Wire(), new Wire(), null, null, null);
			assertFalse("An GateException should be thrown in FullAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new FullAdder(new Wire(), new Wire(), new Wire(), null, null);
			assertFalse("An GateException should be thrown in FullAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new FullAdder(new Wire(), new Wire(), new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in FullAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new FullAdder(null, null, null, null, null);
			assertFalse("An GateException should be thrown in HalfAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
}
