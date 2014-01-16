package simulate;

import junit.framework.TestCase;

public class WorkQueueTest extends TestCase {
	public void testWorkQueue() {
		WorkQueue queue = new WorkQueue();
		assertEquals(false, queue.hasWork());
	}
	
	public void testAdd() {
		WorkQueue queue = new WorkQueue();
		try {
			queue.add(null);
			assertFalse("An WorkException should be thrown in WorkQueue", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			queue.add(new Work(0, new SimulateAction() {
				@Override
				public void invoke() {}
			}));
			queue.add(new Work(1, new SimulateAction() {
				@Override
				public void invoke() {}
			}));
			assertEquals(true, queue.hasWork());
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in WorkQueue", false);
		}
	}
	
	public void testPop() throws WorkException {
		WorkQueue queue = new WorkQueue();
		try {
			queue.pop();
			assertFalse("An WorkException should be thrown in WorkQueue", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		Work works[] = {
			new Work(0, new SimulateAction() { // at 0
				@Override
				public void invoke() {}
			}),
			new Work(2, new SimulateAction() { // at 2
				@Override
				public void invoke() {}
			}),
			new Work(1, new SimulateAction() { // at 1
				@Override
				public void invoke() {}
			}),
			new Work(2, new SimulateAction() { // at 3
				@Override
				public void invoke() {}
			}),
			new Work(3, new SimulateAction() { // at 5
				@Override
				public void invoke() {}
			}),
			new Work(2, new SimulateAction() { // at 4
				@Override
				public void invoke() {}
			})
		};
		for (Work each : works) {
			queue.add(each);
		}
		try {
			assertEquals(works[0], queue.pop());
			assertEquals(works[2], queue.pop());
			assertEquals(works[1], queue.pop());
			assertEquals(works[3], queue.pop());
			assertEquals(works[5], queue.pop());
			assertEquals(works[4], queue.pop());
			assertEquals(false, queue.hasWork());
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in WorkQueue", false);
		}
	}
	
	public void testNextTime() throws WorkException {
		WorkQueue queue = new WorkQueue();
		try {
			queue.nextTime();
			assertFalse("An WorkException should be thrown in WorkQueue", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		queue.add(new Work(0, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		queue.add(new Work(0, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		queue.add(new Work(1, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		queue.add(new Work(2, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		try {
			assertEquals(0, queue.nextTime());
			
			queue.pop();
			assertEquals(0, queue.nextTime());
			
			queue.pop();
			assertEquals(1, queue.nextTime());
			
			queue.pop();
			assertEquals(2, queue.nextTime());
			queue.pop();
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in WorkQueue", false);
		}
	}
	
	public void testHasWork() throws WorkException {
		WorkQueue queue = new WorkQueue();
		assertEquals(false, queue.hasWork());
		
		queue.add(new Work(0, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		assertEquals(true, queue.hasWork());
		
		queue.add(new Work(1, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		assertEquals(true, queue.hasWork());
		
		queue.pop();
		assertEquals(true, queue.hasWork());
		queue.pop();
		assertEquals(false, queue.hasWork());
	}
	
	public void testWorkSize() throws WorkException {
		WorkQueue queue = new WorkQueue();
		assertEquals(0, queue.workSize());
		queue.add(new Work(0, new SimulateAction() {
			@Override
			public void invoke() {}
		}));
		assertEquals(1, queue.workSize());
	}
	
	public void testClassLevel() throws WorkException {
		// path1: E -> N -> E -> N -> E
		WorkQueue queue = new WorkQueue(); // def0
		assertEquals(0, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		}));
		assertEquals(1, queue.workSize());
		queue.pop(); // def1, use4
		assertEquals(0, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def4, use1
			@Override
			public void invoke() {}
		}));
		assertEquals(1, queue.workSize());
		queue.pop(); // def1, use4
		assertEquals(0, queue.workSize());
		
		// path2: E -> N -> aN -> aN -> pN -> aN -> pN -> pN -> E
		queue = new WorkQueue(); // def0
		assertEquals(0, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def0, use1
			@Override
			public void invoke() {}
		}));
		assertEquals(1, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def1, use2
			@Override
			public void invoke() {}
		}));
		assertEquals(2, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def2, use2
			@Override
			public void invoke() {}
		}));
		assertEquals(3, queue.workSize());
		queue.pop(); // def2, use3
		assertEquals(2, queue.workSize());
		queue.add(new Work(0, new SimulateAction() { // def3, use2
			@Override
			public void invoke() {}
		}));
		assertEquals(3, queue.workSize());
		queue.pop(); // def2, use3
		assertEquals(2, queue.workSize());
		queue.pop(); // def3, use3
		assertEquals(1, queue.workSize());
		queue.pop(); // def3, use4
		assertEquals(0, queue.workSize());
	}
}
