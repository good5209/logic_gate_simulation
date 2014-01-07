package wire;

import junit.framework.TestCase;

public class WireProbeTest extends TestCase {
	public void testWireProbe() {
		Wire wire = new Wire();
		TestWireProbe probe = new TestWireProbe(wire);
		wire.setSignal(false);
		assertEquals(false, probe.hasChanged());
		assertEquals(false, probe.hasChanged());
		
		wire.setSignal(true);
		assertEquals(true, probe.hasChanged());
		assertEquals(false, probe.hasChanged());
		
		wire.setSignal(true);
		assertEquals(false, probe.hasChanged());
		assertEquals(false, probe.hasChanged());
		
		wire.setSignal(false);
		assertEquals(true, probe.hasChanged());
		assertEquals(false, probe.hasChanged());
	}
	
	private class TestWireProbe extends WireProbe {
		private boolean changed;
		
		public TestWireProbe(Wire wire) {
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
