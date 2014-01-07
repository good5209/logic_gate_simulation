package wire;

import junit.framework.TestCase;

public class WireArrayTest extends TestCase {
	public void testWireArray() {
		assertEquals(null, WireArray.wireArray(0));
		assertEquals(1, WireArray.wireArray(1).length);
		assertEquals(3, WireArray.wireArray(3).length);
		assertEquals(5, WireArray.wireArray(5, null).length);
		assertEquals(Integer.SIZE, WireArray.wireArray(Integer.SIZE).length);
		assertEquals(Long.SIZE, WireArray.wireArray(Long.SIZE).length);
		assertEquals(Long.SIZE + 1, WireArray.wireArray(Long.SIZE + 1).length);
		
		Wire[] wires = WireArray.wireArray(3, "wire");
		assertEquals(3, wires.length);
		assertEquals("wire0", wires[0].getName());
		assertEquals("wire1", wires[1].getName());
		assertEquals("wire2", wires[2].getName());
	}
	
	public void testSetValue() {
		Wire[] wires = WireArray.wireArray(1);
		WireArray.setValue(0, wires);
		assertEquals(0, WireArray.getValue(wires));
		WireArray.setValue(1, wires);
		assertEquals(1, WireArray.getValue(wires));
		
		wires = WireArray.wireArray(2);
		WireArray.setValue(2, wires);
		assertEquals(2, WireArray.getValue(wires));
		WireArray.setValue(3, wires);
		assertEquals(3, WireArray.getValue(wires));
		
		try {
			WireArray.setValue(0, null);
			assertFalse("An NumberFormatException should be thrown in setValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		try {
			WireArray.setValue(2, WireArray.wireArray(1));
			assertFalse("An NumberFormatException should be thrown in setValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		try {
			WireArray.setValue(4, WireArray.wireArray(2));
			assertFalse("An NumberFormatException should be thrown in setValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		try {
			WireArray.setValue(16, WireArray.wireArray(4));
			assertFalse("An NumberFormatException should be thrown in setValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		
		wires = WireArray.wireArray(4);
		WireArray.setValue(2, wires);
		assertEquals(false, wires[0].getSignal());
		assertEquals(true, wires[1].getSignal());
		assertEquals(false, wires[2].getSignal());
		assertEquals(false, wires[3].getSignal());
		
		WireArray.setValue(13, wires);
		assertEquals(true, wires[0].getSignal());
		assertEquals(false, wires[1].getSignal());
		assertEquals(true, wires[2].getSignal());
		assertEquals(true, wires[2].getSignal());
	}
	
	public void testGetValue() {
		try {
			WireArray.getValue(null);
			assertFalse("An NumberFormatException should be thrown in getValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		try {
			WireArray.getValue(WireArray.wireArray(Long.SIZE + 1));
			assertFalse("An NumberFormatException should be thrown in getValue", true);
		} catch (NumberFormatException e) {
			assertFalse(false);
		}
		assertEquals(0, WireArray.getValue(WireArray.wireArray(1)));
		assertEquals(0, WireArray.getValue(WireArray.wireArray(32)));
		assertEquals(0, WireArray.getValue(WireArray.wireArray(Long.SIZE)));
		
		Wire[] wires = WireArray.wireArray(4);
		wires[0].setSignal(true);
		wires[1].setSignal(false);
		wires[2].setSignal(true);
		wires[3].setSignal(false);
		assertEquals(5, WireArray.getValue(wires));
		
		wires[0].setSignal(false);
		wires[1].setSignal(true);
		wires[2].setSignal(false);
		wires[3].setSignal(true);
		assertEquals(10, WireArray.getValue(wires));
	}
}
