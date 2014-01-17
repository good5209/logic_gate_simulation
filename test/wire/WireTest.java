package wire;

import junit.framework.TestCase;

import gate.*;

public class WireTest extends TestCase {
	public void testWire() {
		new Wire();
	}
	
	public void testGetName() {
		assertEquals("Wire", new Wire().getName());
		assertEquals("Test wire", new Wire("Test wire").getName());
	}
	
	public void testSetAndGetSignal() {
		Wire wire = new Wire();
		assertEquals(false, wire.getSignal()); // default signal
		wire.setSignal(true);
		assertEquals(true, wire.getSignal());
		wire.setSignal(true);
		assertEquals(true, wire.getSignal());
		wire.setSignal(false);
		assertEquals(false, wire.getSignal());
		wire.setSignal(false);
		assertEquals(false, wire.getSignal());
	}
	
	public void testListenSignal() {
		final Wire wire = new Wire();
		try {
			wire.listenSignal(null);
			assertFalse("An WireException should be thrown in listenSignal", true);
		} catch (WireException e) {
			assertFalse(false);
		}
		
		WireListener listener = new WireListener() {
			boolean lastSignal = wire.getSignal();
			
			@Override
			public void wireSignalChanged() {
				assertNotSame("when listener notified, signal should difference with last notify", lastSignal, wire.getSignal());
				lastSignal = wire.getSignal();
			}
		};
		try {
			wire.listenSignal(listener);
			assertTrue(true);
		} catch (WireException e) {
			assertTrue("No WireException should be thrown in listenSignal", false);
		}
		wire.setSignal(false);
		wire.setSignal(true);
		wire.setSignal(true);
		wire.setSignal(false);
		wire.setSignal(false);
	}
	
	public void testWireAttach() {
		try {
			Wire wire = new Wire();
			new DummyGate(new Wire(), wire);
			new DummyGate(wire, new Wire());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		try {
			Wire wire = new Wire();
			new DummyGate(wire, new Wire());
			new DummyGate(wire, new Wire());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		try {
			new DummyGate(new Wire(), new Wire());
		} catch (GateException e) {
			assertTrue(false);
		}
		
		/*
		 * no avoid two gate output wire are same
		 * this seem user layout bug
		 */
		try {
			Wire wire = new Wire();
			new DummyGate(new Wire(), wire);
			new DummyGate(new Wire(), wire);
			//assertFalse("Wire can't attach two gate output", true);
		} catch (GateException e) {
			assertFalse(false);
		}
	}
	
	public void testClassLevel() throws WireException {
		Wire wire = new Wire(); // def0
		final Counter counter = new Counter();
		wire.listenSignal(new WireListener() { // def0, use1
			@Override
			public void wireSignalChanged() {
				counter.add();
			}
		});
		wire.setSignal(!wire.getSignal());
		assertEquals(1, counter.get());
		counter.reset();
		
		wire.listenSignal(new WireListener() { // def1, use2
			@Override
			public void wireSignalChanged() {
				counter.add();
			}
		});
		wire.setSignal(!wire.getSignal());
		assertEquals(2, counter.get());
		counter.reset();
		
		wire.listenSignal(new WireListener() { // def2, use2
			@Override
			public void wireSignalChanged() {
				counter.add();
			}
		});
		wire.setSignal(!wire.getSignal());
		assertEquals(3, counter.get());
		counter.reset();
	}
	
	private class Counter {
		int count = 0;
		
		public void add() {
			count += 1;
		}
		
		public int get() {
			return count;
		}
		
		public void reset() {
			count = 0;
		}
	}
}
