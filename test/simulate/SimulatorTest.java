package simulate;

import junit.framework.TestCase;

public class SimulatorTest extends TestCase {
	public void testSimulator() {
		Simulator sim = new Simulator();
		assertEquals(0, sim.getTime());
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
	}
	
	public void testWorkSize() throws SimulateException {
		Simulator sim = new Simulator();
		assertEquals(0, sim.workSize());
		sim.addAction(0, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
	}
	
	public void testGetTime() throws SimulateException {
		Simulator sim = new Simulator();
		assertEquals(0, sim.getTime());
		sim.addAction(3, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		sim.run();
		assertEquals(3, sim.getTime());
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
		sim.run();
		
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
		try {
			sim.runUntil(-1);
			assertFalse("An SimulateException should be thrown in Simulator", true);
		} catch (SimulateException e) {
			assertFalse(false);
		}
		try {
			sim.runUntil(2);
			assertEquals(0, sim.getTime());
			assertTrue(true);
		} catch (SimulateException e) {
			assertTrue("No SimulateException should be thrown in Simulator", false);
		}
		
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
	
	public void testClassLevel() throws SimulateException {
		// path1: N->aI->aI->aI->rRr->nRr->nRr->nRr->N->aI->E
		Simulator sim = new Simulator(); // def0
		assertEquals(0, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def1, use2
			@Override
			public void invoke() {}
		});
		assertEquals(2, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def2, use2
			@Override
			public void invoke() {}
		});
		assertEquals(3, sim.workSize());
		sim.run(); // (def2, use3), (def3, use4), (def4, use4), (def4, use5)
		assertEquals(0, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def5, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		
		// path2: N->aI->aI->rURrU->tUpI->aI->rURrU->nRrU->nRrU->tUpI->rURrU->nRrU->N->aI->E
		sim = new Simulator(); // def0
		assertEquals(0, sim.workSize());
		sim.addAction(1, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		sim.addAction(1, new SimulateAction() { // def1, use2
			@Override
			public void invoke() {}
		});
		assertEquals(2, sim.workSize());
		sim.runUntil(0); // (def2, use6), (def6, use8)
		assertEquals(2, sim.workSize());
		sim.addAction(2, new SimulateAction() { // def8, use2
			@Override
			public void invoke() {}
		});
		assertEquals(3, sim.workSize());
		sim.runUntil(1); // (def2, use6), (def6, use7), (def7, use7), (def7, use8)
		assertEquals(1, sim.workSize());
		sim.runUntil(1); // (def2, use6), (def6, use7), (def7, use9)
		assertEquals(0, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def9, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		
		// path3: N->aI->rRr->nRr->N->E
		sim = new Simulator(); // def0
		assertEquals(0, sim.workSize());
		sim.addAction(0, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		sim.run(); // (def1, use3), (def3, use4), (def4, use5)
		assertEquals(0, sim.workSize());
		
		// path4: N->aI->rURrU->tUpI->rRr->nRr->N->E
		sim = new Simulator(); // def0
		assertEquals(0, sim.workSize());
		sim.addAction(1, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		});
		assertEquals(1, sim.workSize());
		sim.runUntil(0); // (def1, use6), (def6, use8)
		assertEquals(1, sim.workSize());
		sim.run(); // (def8, use3), (def3, use4), (def4, use5)
		assertEquals(0, sim.workSize());
	}
}
