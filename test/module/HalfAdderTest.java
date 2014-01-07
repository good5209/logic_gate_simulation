package module;

import junit.framework.TestCase;

import simulate.*;
import gate.GateException;
import wire.*;

public class HalfAdderTest extends TestCase {

	public void testHalfAdder() {
		try {
			Simulator sim = new Simulator();
			Wire input1 = new Wire("input1");
			Wire input2 = new Wire("input2");
			Wire sum = new Wire("sum");
			Wire carry = new Wire("carry");
			new WireProbe(sum);
			new WireProbe(carry);
			sim.addComponent(new HalfAdder(input1, input2, sum, carry));
			
			input1.setSignal(false);
			input2.setSignal(false);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(false, carry.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(false);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(false, carry.getSignal());
			
			input1.setSignal(false);
			input2.setSignal(true);
			sim.run();
			assertEquals(true, sum.getSignal());
			assertEquals(false, carry.getSignal());
			
			input1.setSignal(true);
			input2.setSignal(true);
			sim.run();
			assertEquals(false, sum.getSignal());
			assertEquals(true, carry.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		try {
			new HalfAdder(new Wire(), null, null, null);
			assertFalse("An GateException should be thrown in HalfAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new HalfAdder(new Wire(), new Wire(), null, null);
			assertFalse("An GateException should be thrown in HalfAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new HalfAdder(new Wire(), new Wire(), new Wire(), null);
			assertFalse("An GateException should be thrown in HalfAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new HalfAdder(null, null, null, null);
			assertFalse("An GateException should be thrown in HalfAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
}
