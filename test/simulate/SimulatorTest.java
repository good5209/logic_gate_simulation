package simulate;

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
						simulator.addAction(0, new SimulateAction() {
							@Override
							public void invoke() {}
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
	
	public void testAddAction() {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		try {
			sim.addAction(0, null);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		
		try {
			sim.addAction(-1, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		
		try {
			sim.addAction(0, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			sim.addAction(1, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			assertTrue(true);
		} catch (SimulateException e) {
			assertTrue("No SimulateException should be thrown in Simulator", false);
		}
	}
	
	public void testHasWork() throws SimulateException {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		sim.addAction(0, new SimulateAction() {
			@Override
			public void invoke() {}
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
		
		sim.addAction(1, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.next();
		assertEquals(1, sim.getTime());
		
		sim.addAction(3, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.next();
		assertEquals(4, sim.getTime());
		
		sim.addAction(7, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.next();
		assertEquals(11, sim.getTime());
	}
	
	public void testRun() throws SimulateException {
		Simulator sim = new Simulator();
		sim.addAction(1, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.run();
		assertEquals(1, sim.getTime());
		
		sim.addAction(2, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.addAction(3, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.run();
		assertEquals(4, sim.getTime());
	}
	
	public void testRunUntil() throws SimulateException {
		Simulator sim = new Simulator();
		sim.addAction(1, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.addAction(2, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.addAction(3, new SimulateAction() {
			@Override
			public void invoke() {}
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
		sim.addAction(1, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		try {
			sim.runUntil(-1);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
	}
}
