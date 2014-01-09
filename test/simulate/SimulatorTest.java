package simulate;

import gate.GateAction;
import junit.framework.TestCase;

public class SimulatorTest extends TestCase {
	public void testSimulator() {
		new Simulator();
	}
	
	public void testAddComponent() {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		try {
			sim.addComponent(null);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		
		try {
			sim.addComponent(new SimulateComponent() {
				@Override
				public void addOnSimulator(Simulator simulator) {
					try {
						simulator.addGateAction(0, new GateAction() {
							@Override
							public void invokeAction() {}
						});
						assertTrue(true);
					} catch (SimulateException e) {
						assertTrue("No SimulateException should be thrown in Simulator", false);
					}
				}
			});
			assertTrue(true);
		} catch (SimulateException e) {
			assertTrue("No SimulateException should be thrown in Simulator", false);
		}
	}
	
	public void testAddGateAction() {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		try {
			sim.addGateAction(0, null);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		
		try {
			sim.addGateAction(-1, new GateAction() {
				@Override
				public void invokeAction() {}
			});
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		
		try {
			sim.addGateAction(0, new GateAction() {
				@Override
				public void invokeAction() {}
			});
			sim.addGateAction(1, new GateAction() {
				@Override
				public void invokeAction() {}
			});
			assertTrue(true);
		} catch (SimulateException e) {
			assertTrue("No SimulateException should be thrown in Simulator", false);
		}
	}
	
	public void testHasWork() throws SimulateException {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		sim.addGateAction(0, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		assertEquals(true, sim.hasWork());
		
		sim.run();
		assertEquals(false, sim.hasWork());
	}
	
	public void testGetTime() {
		Simulator sim = new Simulator();
		assertEquals(0, sim.getTime());
	}
	
	public void testNext() throws SimulateException {
		Simulator sim = new Simulator();
		
		assertEquals(0, sim.getTime());
		sim.next();
		assertEquals(0, sim.getTime());
		
		sim.addGateAction(1, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.next();
		assertEquals(1, sim.getTime());
		
		sim.addGateAction(3, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.next();
		assertEquals(4, sim.getTime());
		
		sim.addGateAction(7, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.next();
		assertEquals(11, sim.getTime());
	}
	
	public void testRun() throws SimulateException {
		Simulator sim = new Simulator();
		sim.addGateAction(1, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.run();
		assertEquals(1, sim.getTime());
		
		sim.addGateAction(2, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.addGateAction(3, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.run();
		assertEquals(4, sim.getTime());
	}
	
	public void testRunUntil() throws SimulateException {
		Simulator sim = new Simulator();
		sim.addGateAction(1, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.addGateAction(2, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.addGateAction(3, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		try {
			sim.runUntil(0);
			assertEquals(0, sim.getTime());
			sim.runUntil(2);
			assertEquals(2, sim.getTime());
			sim.runUntil(0);
			assertEquals(2, sim.getTime());
			sim.runUntil(2);
			assertEquals(3, sim.getTime());
			assertTrue(true);
		} catch (SimulateException e) {
			assertTrue("No SimulateException should be thrown in Simulator", false);
		}
		
		sim = new Simulator();
		sim.addGateAction(1, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		try {
			sim.runUntil(-1);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
	}
}
