package wire;

import junit.framework.TestCase;

public class WireProbeTest extends TestCase {
	public void testWireProbe() {
		try {
			new TestWireProbe(null);
			assertFalse("An WireException should be thrown in WireProbe", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		
		try {
			new TestWireProbe(new Wire());
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in WireProbe", false);
		}
	}
	
	public void testWireSignalChanged() {
		Wire wire = new Wire();
		try {
			TestWireProbe probe = new TestWireProbe(wire);
			wire.setSignal(false);
			assertEquals(false, probe.hasChanged());
			assertEquals(false, probe.hasChanged());
			
			wire.setSignal(true); // change signal
			assertEquals(true, probe.hasChanged());
			assertEquals(false, probe.hasChanged());
			
			wire.setSignal(true); // signal not change
			assertEquals(false, probe.hasChanged());
			assertEquals(false, probe.hasChanged());
			
			wire.setSignal(false); // change signal
			assertEquals(true, probe.hasChanged());
			assertEquals(false, probe.hasChanged());
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in WireProbe", false);
		}
	}
	
	private class TestWireProbe extends WireProbe {
		private boolean changed;
		
		public TestWireProbe(Wire wire) throws WireException {
			super(wire);
		}
		
		@Override
		public void wireSignalChanged() {
			super.wireSignalChanged();
			changed = true;
		}
		
		public boolean hasChanged() {
			boolean result = changed;
			changed = false;
			return result;
		}
	}
}
