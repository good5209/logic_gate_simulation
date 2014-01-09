package simulate;

import junit.framework.TestCase;

public class WorkQueueTest extends TestCase {
	public void testWorkQueue() {
		new WorkQueue();
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
			queue.add(new Work(0) {
				@Override
				public void invoke() {}
			});
			queue.add(new Work(1) {
				@Override
				public void invoke() {}
			});
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
			new Work(0) { // at 0
				@Override
				public void invoke() {}
			},
			new Work(2) { // at 2
				@Override
				public void invoke() {}
			},
			new Work(1) { // at 1
				@Override
				public void invoke() {}
			},
			new Work(2) { // at 3
				@Override
				public void invoke() {}
			},
			new Work(3) { // at 5
				@Override
				public void invoke() {}
			},
			new Work(2) { // at 4
				@Override
				public void invoke() {}
			}
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
			assertEquals(-1, queue.nextTime());
			assertFalse("An WorkException should be thrown in WorkQueue", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		queue.add(new Work(0) {
			@Override
			public void invoke() {}
		});
		queue.add(new Work(0) {
			@Override
			public void invoke() {}
		});
		queue.add(new Work(1) {
			@Override
			public void invoke() {}
		});
		queue.add(new Work(2) {
			@Override
			public void invoke() {}
		});
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
		
		queue.add(new Work(0) {
			@Override
			public void invoke() {}
		});
		assertEquals(true, queue.hasWork());
		
		queue.add(new Work(1) {
			@Override
			public void invoke() {}
		});
		assertEquals(true, queue.hasWork());
		
		queue.pop();
		assertEquals(true, queue.hasWork());
		queue.pop();
		assertEquals(false, queue.hasWork());
	}
}
