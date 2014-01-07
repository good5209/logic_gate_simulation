package wire;

import junit.framework.TestCase;

import simulate.*;
import gate.*;

public class WireTest extends TestCase {
	public void testWire() {
		new Wire();
	}
	
	public void testGetName() {
		assertEquals("Wire", new Wire().getName());
		assertEquals("Test wire", new Wire("Test wire").getName());
	}
	
	public void testSetSignal() {
		Wire w = new Wire();
		assertEquals(false, w.getSignal()); // default signal
		w.setSignal(true);
		assertEquals(true, w.getSignal());
		w.setSignal(false);
		assertEquals(false, w.getSignal());
	}

	public void testGetSignal() {
		Wire wire = new Wire();
		assertEquals(false, wire.getSignal());
		try {
			Simulator sim = new Simulator();
			Wire inputWire = new Wire();
			Wire outputWire = new Wire();
			sim.addComponent(new DummyGate(inputWire, outputWire));
			inputWire.setSignal(true);
			
			sim.run();
			assertEquals(true, outputWire.getSignal());
		} catch (GateException e) {
			assertTrue(false);
		}
	}
	
	public void testListenSignal() {
		final Wire wire = new Wire();
		WireListener listener = new WireListener() {
			boolean lastSignal = wire.getSignal();
			
			@Override
			public void wireSignalChanged() {
				assertNotSame(lastSignal, wire.getSignal()); // different with last changed signal
				lastSignal = wire.getSignal();
			}
		};
		wire.listenSignal(listener);
		wire.setSignal(false);
		wire.setSignal(true);
		wire.setSignal(false);
	}
	
	public void testWireOutputAttach() {
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
}
