package wire;

import junit.framework.TestCase;

public class WireArrayTest extends TestCase {
	public void testWireArray() {
		try {
			WireArray.wireArray(0);
			WireArray.wireArray(Long.SIZE + 1);
			assertFalse("An WireException should be thrown in WireArray", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		
		try {
			assertEquals(1, WireArray.wireArray(1).length);
			assertEquals(3, WireArray.wireArray(3).length);
			assertEquals(5, WireArray.wireArray(5, null).length);
			assertEquals(Integer.SIZE, WireArray.wireArray(Integer.SIZE).length);
			assertEquals(Long.SIZE, WireArray.wireArray(Long.SIZE).length);
			
			Wire[] wires = WireArray.wireArray(3, "wire");
			assertEquals(3, wires.length);
			assertEquals("wire0", wires[0].getName());
			assertEquals("wire1", wires[1].getName());
			assertEquals("wire2", wires[2].getName());
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in WireArray", false);
		}
	}
	
	public void testSetValue() {
		try {
			WireArray.setValue(0, null);
			assertFalse("An WireException should be thrown in setValue", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		try { // overflow, "2" can't save in 1 bit
			WireArray.setValue(2, WireArray.wireArray(1));
			assertFalse("An WireException should be thrown in setValue", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		try { // overflow, "4" can't save in 2 bits
			WireArray.setValue(4, WireArray.wireArray(2));
			assertFalse("An WireException should be thrown in setValue", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		try { // overflow, "16" can't save in 4 bits
			WireArray.setValue(16, WireArray.wireArray(4));
			assertFalse("An WireException should be thrown in setValue", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		
		// TODO, WireArray.setValue(Long.MAX_VALUE + 1, )
		
		try {
			Wire[] wires = WireArray.wireArray(1); // 1 bit
			WireArray.setValue(0, wires);
			assertEquals(0, WireArray.getValue(wires));
			WireArray.setValue(1, wires);
			assertEquals(1, WireArray.getValue(wires));
			
			wires = WireArray.wireArray(2); // 2 bits
			WireArray.setValue(2, wires);
			assertEquals(2, WireArray.getValue(wires));
			WireArray.setValue(3, wires);
			assertEquals(3, WireArray.getValue(wires));
			
			wires = WireArray.wireArray(4); // 4 bits
			WireArray.setValue(2, wires); // 0b0010
			assertEquals(false, wires[0].getSignal());
			assertEquals(true, wires[1].getSignal());
			assertEquals(false, wires[2].getSignal());
			assertEquals(false, wires[3].getSignal());
			
			WireArray.setValue(13, wires); // 0b1101
			assertEquals(true, wires[0].getSignal());
			assertEquals(false, wires[1].getSignal());
			assertEquals(true, wires[2].getSignal());
			assertEquals(true, wires[2].getSignal());
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in setValue", false);
		}
	}
	
	public void testGetValue() {
		try {
			WireArray.getValue(null);
			assertFalse("An WireException should be thrown in getValue", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		
		try {
			assertEquals(0, WireArray.getValue(WireArray.wireArray(1))); // default value
			assertEquals(0, WireArray.getValue(WireArray.wireArray(32)));
			assertEquals(0, WireArray.getValue(WireArray.wireArray(Long.SIZE)));
			
			Wire[] wires = WireArray.wireArray(4);
			wires[0].setSignal(true); // 0b0101
			wires[1].setSignal(false);
			wires[2].setSignal(true);
			wires[3].setSignal(false);
			assertEquals(5, WireArray.getValue(wires));
			
			wires[0].setSignal(false); // 0b1010
			wires[1].setSignal(true);
			wires[2].setSignal(false);
			wires[3].setSignal(true);
			assertEquals(10, WireArray.getValue(wires));
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in getValue", false);
		}
	}
}
