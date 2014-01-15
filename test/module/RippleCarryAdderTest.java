package module;

import junit.framework.TestCase;

import simulate.*;
import gate.GateException;
import wire.*;

public class RippleCarryAdderTest extends TestCase {

	public void testRippleCarryAdder() throws WireException {
		try {
			Simulator sim = new Simulator();
			Wire[] as = {new Wire("a1"), new Wire("a2"), new Wire("a3"), new Wire("a4")};
			Wire[] bs = {new Wire("b1"), new Wire("b2"), new Wire("b3"), new Wire("b4")};
			Wire[] sums = {new Wire("s1"), new Wire("s2"), new Wire("s3"), new Wire("s4")};
			Wire carry = new Wire("carry");
			for (Wire each : sums) {
				new WireProbe(each);
			}
			new WireProbe(carry);
			sim.addComponent(new RippleCarryAdder(4, as, bs, sums, carry));
			
			/*
			 * 0 + 0 = 0
			 */
			for (Wire each : as) {
				each.setSignal(false);
			}
			for (Wire each : bs) {
				each.setSignal(false);
			}
			sim.run();
			for (Wire each : sums) {
				assertEquals(false, each.getSignal());
			}
			assertEquals(false, carry.getSignal());
			
			/*
			 * 2 + 9 = 11
			 */
			as[1].setSignal(true);
			bs[0].setSignal(true);
			bs[3].setSignal(true);
			sim.run();
			assertEquals(true, sums[0].getSignal());
			assertEquals(true, sums[1].getSignal());
			assertEquals(false, sums[2].getSignal());
			assertEquals(true, sums[3].getSignal());
			assertEquals(false, carry.getSignal());
			
			/*
			 * 6 + 7 = 13
			 */
			as[0].setSignal(false);
			as[1].setSignal(true);
			as[2].setSignal(true);
			as[3].setSignal(false);
			bs[0].setSignal(true);
			bs[1].setSignal(true);
			bs[2].setSignal(true);
			bs[3].setSignal(false);
			sim.run();
			assertEquals(true, sums[0].getSignal());
			assertEquals(false, sums[1].getSignal());
			assertEquals(true, sums[2].getSignal());
			assertEquals(true, sums[3].getSignal());
			assertEquals(false, carry.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
		
		try {
			new RippleCarryAdder(0, null, null, null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[3], null, null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[4], null, null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[4], new Wire[3], null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[4], new Wire[4], null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[4], new Wire[4], new Wire[3], null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, new Wire[4], new Wire[4], new Wire[4], null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
		
		try {
			new RippleCarryAdder(4, null, null, null, null);
			assertFalse("An GateException should be thrown in RippleCarryAdder", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
	
	public void testBigNumber() throws WireException {
		try {
			Simulator sim = new Simulator();
			Wire[] as = WireArray.wireArray(32, "a");
			Wire[] bs = WireArray.wireArray(32, "b");
			Wire[] sums = WireArray.wireArray(32, "sum");
			Wire carry = new Wire("carry");
			sim.addComponent(new RippleCarryAdder(32, as, bs, sums, carry));
			for (Wire each : sums) {
				new WireProbe(each);
			}
			
			WireArray.setValue(123456789L, as);
			WireArray.setValue(987654321L, bs);
			sim.run();
			assertEquals(1111111110L, WireArray.getValue(sums));
		} catch (GateException e) {
			assertTrue(false);
		} catch (SimulateException e) {
			assertTrue(false);
		}
	}
}
