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
		
		sim.addComponent(null);
		assertEquals(false, sim.hasWork());
		
		sim.addComponent(new SimulateComponent() {
			@Override
			public void addOnSimulator(Simulator simulator) {
				simulator.addGateAction(0, new GateAction() {
					@Override
					public void invokeAction() {}
				});
			}
		});
		assertEquals(true, sim.hasWork());
	}
	
	public void testAddWork() {
		Simulator sim = new Simulator();
		assertEquals(false, sim.hasWork());
		
		sim.addGateAction(0, null);
		assertEquals(false, sim.hasWork());
		
		sim.addGateAction(0, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		assertEquals(true, sim.hasWork());
	}
	
	public void testHasWork() {
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
	
	public void testElapse() {
		Simulator sim = new Simulator();
		assertEquals(0, sim.getTime());
		sim.elapse(3);
		assertEquals(3, sim.getTime());
		sim.elapse(5);
		assertEquals(8, sim.getTime());
	}
	
	public void testNext() {
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
	
	public void testRun() {
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
	
	public void testRunUntil() {
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
		sim.runUntil(0);
		assertEquals(0, sim.getTime());
		sim.runUntil(2);
		assertEquals(2, sim.getTime());
		sim.runUntil(0);
		assertEquals(2, sim.getTime());
		sim.runUntil(2);
		assertEquals(3, sim.getTime());
		
		sim = new Simulator();
		sim.runUntil(-1);
		assertEquals(0, sim.getTime());
		sim.addGateAction(1, new GateAction() {
			@Override
			public void invokeAction() {}
		});
		sim.runUntil(-1);
		assertEquals(0, sim.getTime());
		sim.runUntil(2);
		assertEquals(1, sim.getTime());
		sim.runUntil(-1);
		assertEquals(1, sim.getTime());
	}
}
